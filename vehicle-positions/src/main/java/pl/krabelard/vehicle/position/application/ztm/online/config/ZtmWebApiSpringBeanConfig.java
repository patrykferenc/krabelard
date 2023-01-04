package pl.krabelard.vehicle.position.application.ztm.online.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.krabelard.vehicle.position.application.ztm.ZtmApiVehiclePositionRepository;
import pl.krabelard.vehicle.position.application.ztm.online.ZtmWebApiClientSpringBeanConfig;

@Configuration
@Import(ZtmWebApiClientSpringBeanConfig.class)
@RequiredArgsConstructor
public class ZtmWebApiSpringBeanConfig {

	private final ZtmWebApiClientSpringBeanConfig ztmWebApiClientSpringBeanConfig;

	@Bean
	public ZtmApiVehiclePositionRepository ztmApiVehiclePositionRepository() {
		return new ZtmApiVehiclePositionRepository(
			ztmWebApiClientSpringBeanConfig.ztmApiVehiclePositionRetrievingService()
		);
	}
}
