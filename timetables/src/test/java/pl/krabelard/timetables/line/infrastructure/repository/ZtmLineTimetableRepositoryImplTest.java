package pl.krabelard.timetables.line.infrastructure.repository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import pl.krabelard.timetables.line.domain.entity.BusStop;
import pl.krabelard.timetables.line.domain.entity.LineTimetable;
import pl.krabelard.timetables.line.domain.exception.LineTimetableFetchingException;
import pl.krabelard.timetables.line.domain.usecase.ZtmLineTimetableRepository;
import pl.krabelard.timetables.line.infrastructure.repository.resource.ZtmLineTimetableApiResource;
import pl.krabelard.timetables.line.infrastructure.repository.resource.ZtmLineTimetableMapper;
import pl.krabelard.timetables.line.infrastructure.repository.resource.ZtmLineTimetableMapperImpl;

public class ZtmLineTimetableRepositoryImplTest {

	private static final String MOCK_API_KEY = "key";
	private static final String MOCK_QUERY_ID = "id";
	private static final String MOCK_QUERY_STRING =
		"https://api.um.warszawa.pl/api/action/dbtimetable_get?";
	private static final String MOCK_QUERY_PARAM_STRING =
		"%sid=%s&busstopId=%s&busstopNr=%02d&line=%d&apikey=%s";
	private RestTemplate restTemplate;
	private ZtmLineTimetableRepository repository;

	@BeforeEach
	void setup() {
		ZtmLineTimetableMapper mapper = new ZtmLineTimetableMapperImpl();
		restTemplate = mock(RestTemplate.class);
		repository =
			new ZtmLineTimetableRepositoryImpl(
				restTemplate,
				mapper,
				MOCK_QUERY_ID,
				MOCK_QUERY_STRING,
				MOCK_QUERY_PARAM_STRING,
				MOCK_API_KEY
			);
	}

	@Test
	void whenGettingTimetablesFromRepository_shouldCallGetOnAPreparedQueryString() {
		//given
		var stop = new BusStop("7009", 1, 520);
		var queryResult = new ZtmLineTimetableApiResource(
			List.of(
				new ZtmLineTimetableApiResource.Value(
					List.of(new ZtmLineTimetableApiResource.KeyValue("czas", "2"))
				)
			)
		);
		var expectedQuery =
			"https://api.um.warszawa.pl/api/action/dbtimetable_get?id=id&busstopId=7009&busstopNr=01&line=520&apikey=key";
		var expectedResult = new LineTimetable(List.of("2"));
		when(
			restTemplate.getForObject(
				expectedQuery,
				ZtmLineTimetableApiResource.class
			)
		)
			.thenReturn(queryResult);
		//when
		var result = repository.getFor(stop);
		//then
		Assertions
			.assertThat(result.arrivals())
			.isEqualTo(expectedResult.arrivals());
	}

	@Test
	void whenGettingTimetablesFromRepositoryWithNoEmptyResponse_shouldThrowLineTimetableFetchingException() {
		//given
		var stop = new BusStop("7009", 1, 520);
		//when
		var queryResult = new ZtmLineTimetableApiResource(Collections.emptyList());
		var expectedQuery =
			"https://api.um.warszawa.pl/api/action/dbtimetable_get?id=id&busstopId=7009&busstopNr=01&line=520&apikey=key";
		when(
			restTemplate.getForObject(
				expectedQuery,
				ZtmLineTimetableApiResource.class
			)
		)
			.thenReturn(queryResult);
		//then
		Assertions
			.assertThatThrownBy(() -> repository.getFor(stop))
			.isInstanceOf(LineTimetableFetchingException.class)
			.hasMessage(
				"There are no timetables characterised by given query parameters"
			);
	}

	@Test
	void whenGettingTimetablesFromRepositoryWithNoResponse_shouldThrowLineTimetableFetchingException() {
		//given
		var stop = new BusStop("7009", 1, 520);
		//when - then
		Assertions
			.assertThatThrownBy(() -> repository.getFor(stop))
			.isInstanceOf(LineTimetableFetchingException.class)
			.hasMessage("Error fetching data from ZTM repository");
	}
}
