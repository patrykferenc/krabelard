package pl.krabelard.timetables.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import pl.krabelard.timetables.line.domain.usecase.ZtmLineTimetableRepository;
import pl.krabelard.timetables.line.infrastructure.repository.ZtmLineTimetableRepositoryImpl;
import pl.krabelard.timetables.line.infrastructure.repository.resource.ZtmLineTimetableMapper;
import pl.krabelard.timetables.line.infrastructure.repository.resource.ZtmLineTimetableMapperImpl;

@Configuration
@PropertySource("classpath:application.properties")
public class TimetablesApplicationConfiguration {

	@Value("${ztm.api.key}")
	private String ZTM_API_KEY;

	@Value("${ztm.api.timetable.id}")
	private String ZTM_API_TIMETABLE_DB_ID;

	@Value("${ztm.api.param.uri}")
	private String ZTM_API_PARAM_URI;

	@Value("${ztm.api.base.uri}")
	private String ZTM_API_BASE_URI;

	@Bean
	ZtmLineTimetableMapper ztmLineTimetableMapper() {
		return new ZtmLineTimetableMapperImpl();
	}

	@Bean
	ZtmLineTimetableRepository ztmLineTimetableRepository(
		RestTemplateBuilder templateBuilder,
		ZtmLineTimetableMapper mapper
	) {
		return new ZtmLineTimetableRepositoryImpl(
			ZTM_API_KEY,
			ZTM_API_TIMETABLE_DB_ID,
			ZTM_API_PARAM_URI,
			ZTM_API_BASE_URI,
			templateBuilder,
			mapper
		);
	}
}
