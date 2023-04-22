package pl.krabelard.vehicle.position.application.ztm.online.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.krabelard.vehicle.position.application.ztm.ZtmApiVehiclePositionRepository;
import pl.krabelard.vehicle.position.application.ztm.online.ZtmApiVehiclePositionRetrievingService;

@Configuration
@RequiredArgsConstructor
class ZtmWebApiSpringBeanConfig {

	@Bean
	public ZtmApiVehiclePositionRepository ztmApiVehiclePositionRepository(ZtmApiVehiclePositionRetrievingService ztmApiVehiclePositionRetrievingService) {
		return new ZtmApiVehiclePositionRepository(
				ztmApiVehiclePositionRetrievingService
		);
	}
}
