package pl.krabelard.timetables.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.krabelard.timetables.line.infrastructure.repository.resource.ZtmLineTimetableMapper;
import pl.krabelard.timetables.line.infrastructure.repository.resource.ZtmLineTimetableMapperImpl;

@Configuration
public class BeanFactory {

	@Bean
	ZtmLineTimetableMapper ztmLineTimetableMapper() {
		return new ZtmLineTimetableMapperImpl();
	}
}
