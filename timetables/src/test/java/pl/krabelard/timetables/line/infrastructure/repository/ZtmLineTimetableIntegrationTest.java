package pl.krabelard.timetables.line.infrastructure.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.reactive.function.client.WebClient;
import pl.krabelard.timetables.configuration.TimetablesApplicationConfiguration;
import pl.krabelard.timetables.line.domain.entity.BusStop;
import pl.krabelard.timetables.line.domain.entity.LineTimetable;
import pl.krabelard.timetables.line.domain.exception.LineTimetableFetchingException;
import pl.krabelard.timetables.line.domain.usecase.LineTimetableService;
import pl.krabelard.timetables.line.domain.usecase.ZtmLineTimetableRepository;
import pl.krabelard.timetables.line.infrastructure.repository.resource.ZtmLineTimetableMapperImpl;
import reactor.netty.http.client.HttpClient;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class ZtmLineTimetableIntegrationTest {

	@TestConfiguration
	public static class TestConfig {

		@Value("${ztm.api.key}")
		private String ZTM_API_KEY;

		@Value("${ztm.api.timetable.id}")
		private String ZTM_API_TIMETABLE_DB_ID;

		@Value("${ztm.api.base.uri}")
		private String ZTM_API_BASE_URI;

		@Bean
		@Primary
		WebClient testClient(WebClient.Builder builder) {
			return builder
				.clientConnector(
					new ReactorClientHttpConnector(
						HttpClient.create().followRedirect(true)
					)
				)
				.baseUrl(ZTM_API_BASE_URI)
				.build();
		}

		@Bean
		@Primary
		ZtmLineTimetableRepository ztmLineTimetableRepositoryTest(
			WebClient client
		) {
			return new ZtmLineTimetableRepositoryImpl(
				ZTM_API_KEY,
				ZTM_API_TIMETABLE_DB_ID,
				client,
				new ZtmLineTimetableMapperImpl()
			);
		}
	}

	@MockBean
	private LineTimetableService service;

	@MockBean
	private TimetablesApplicationConfiguration timetablesApplicationConfiguration;

	@Autowired
	private WebClient client;

	@Autowired
	private ZtmLineTimetableRepository repository;

	@Test
	void whenQueryingForNotExistingEntry_shouldThrowLineTimetableFetchingException() {
		Assertions
			.assertThatThrownBy(() ->
				repository.getFor(new BusStop("not_existing", -1, -1))
			)
			.isInstanceOf(LineTimetableFetchingException.class)
			.hasMessage(
				"There are no timetables characterised by given query parameters"
			);
	}

	@Test
	void whenQueryingForPossiblyExistingEntry_shouldReturnCorrectlyParsedObject() {
		var response = repository.getFor(new BusStop("7009", 1, 520));
		Assertions.assertThat(response).isInstanceOf(LineTimetable.class);
		Assertions.assertThat(response.arrivals()).isNotEmpty();
	}
}
