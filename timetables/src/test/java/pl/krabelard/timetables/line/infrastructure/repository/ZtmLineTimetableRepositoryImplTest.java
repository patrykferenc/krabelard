package pl.krabelard.timetables.line.infrastructure.repository;

import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import pl.krabelard.timetables.line.domain.entity.BusStop;
import pl.krabelard.timetables.line.domain.entity.LineTimetable;
import pl.krabelard.timetables.line.domain.exception.LineTimetableFetchingException;
import pl.krabelard.timetables.line.domain.usecase.ZtmLineTimetableRepository;
import pl.krabelard.timetables.line.infrastructure.repository.resource.ZtmLineTimetableApiResource;
import pl.krabelard.timetables.line.infrastructure.repository.resource.ZtmLineTimetableMapper;
import pl.krabelard.timetables.line.infrastructure.repository.resource.ZtmLineTimetableMapperImpl;

@TestPropertySource("classpath:application-test.properties")
@ExtendWith({ MockitoExtension.class, SpringExtension.class })
public class ZtmLineTimetableRepositoryImplTest {

	@Value("${ztm.test.key}")
	private String MOCK_API_KEY;

	@Value("${ztm.test.database.id}")
	private String MOCK_QUERY_ID;

	@Value("${ztm.test.base.uri}")
	private String MOCK_QUERY_STRING;

	@Value("${ztm.test.param.uri}")
	private String MOCK_QUERY_PARAM_STRING;

	@Mock
	private RestTemplate restTemplate;

	@Spy
	ZtmLineTimetableMapper mapper = new ZtmLineTimetableMapperImpl();

	private ZtmLineTimetableRepository repository;

	@BeforeEach
	void setup() {
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
					List.of(new ZtmLineTimetableApiResource.KeyValue("czas", "12:00:00"))
				)
			)
		);
		var expectedQuery =
			"https://api.um.warszawa.pl/api/action/dbtimetable_get?id=id&busstopId=7009&busstopNr=01&line=520&apikey=key";
		var expectedResult = new LineTimetable(List.of(LocalTime.NOON));
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
