package pl.krabelard.vehicle.position.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.krabelard.vehicle.position.application.ztm.ZtmApiVehiclePositionRepository;
import pl.krabelard.vehicle.position.domain.VehiclePositionsFacade;

@Configuration
@RequiredArgsConstructor
class VehiclePositionsSpringBeanConfig {

	@Bean
	public VehiclePositionsFacade vehiclePositionsFacade(ZtmApiVehiclePositionRepository ztmApiVehiclePositionRepository) {
		return new VehiclePositionsFacade( ztmApiVehiclePositionRepository
		);
	}
}
