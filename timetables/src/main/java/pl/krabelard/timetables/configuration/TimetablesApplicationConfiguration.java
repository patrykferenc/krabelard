package pl.krabelard.timetables.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import pl.krabelard.timetables.line.domain.usecase.ZtmLineTimetableRepository;
import pl.krabelard.timetables.line.infrastructure.repository.ZtmLineTimetableRepositoryImpl;
import pl.krabelard.timetables.line.infrastructure.repository.resource.ZtmLineTimetableMapper;
import pl.krabelard.timetables.line.infrastructure.repository.resource.ZtmLineTimetableMapperImpl;
import reactor.netty.http.client.HttpClient;

@Configuration
@PropertySource("classpath:application.properties")
public class TimetablesApplicationConfiguration {

	@Value("${ztm.api.key}")
	private String ZTM_API_KEY;

	@Value("${ztm.api.timetable.id}")
	private String ZTM_API_TIMETABLE_DB_ID;

	@Value("${ztm.api.base.uri}")
	private String ZTM_API_BASE_URI;

	@Bean
	ZtmLineTimetableMapper ztmLineTimetableMapper() {
		return new ZtmLineTimetableMapperImpl();
	}

	@Bean
	WebClient webClient(WebClient.Builder builder) {
		return builder
			.clientConnector(new ReactorClientHttpConnector(HttpClient.create().followRedirect(true)))
			.baseUrl(ZTM_API_BASE_URI)
			.build();
	}

	@Bean
	ZtmLineTimetableRepository ztmLineTimetableRepository(
		ZtmLineTimetableMapper mapper,
		WebClient client
	) {
		return new ZtmLineTimetableRepositoryImpl(ZTM_API_KEY, ZTM_API_TIMETABLE_DB_ID, client, mapper);
	}
}
