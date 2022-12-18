package pl.krabelard.vehicle.position.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.krabelard.vehicle.position.application.ztm.ZtmApiVehiclePositionRepository;
import pl.krabelard.vehicle.position.domain.VehiclePositionsFacade;

@Configuration
public class VehiclePositionsSpringBeanConfig {

	@Bean
	public VehiclePositionsFacade vehiclePositionsFacade() {
		return new VehiclePositionsFacade(new ZtmApiVehiclePositionRepository());
	}
}
