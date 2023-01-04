package pl.krabelard.vehicle.position.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.krabelard.vehicle.position.application.ztm.online.config.ZtmWebApiSpringBeanConfig;
import pl.krabelard.vehicle.position.domain.VehiclePositionsFacade;

@Configuration
@Import(ZtmWebApiSpringBeanConfig.class)
@RequiredArgsConstructor
public class VehiclePositionsSpringBeanConfig {

	private final ZtmWebApiSpringBeanConfig ztmWebApiSpringBeanConfig;

	@Bean
	public VehiclePositionsFacade vehiclePositionsFacade() {
		return new VehiclePositionsFacade(
			ztmWebApiSpringBeanConfig.ztmApiVehiclePositionRepository()
		);
	}
}
