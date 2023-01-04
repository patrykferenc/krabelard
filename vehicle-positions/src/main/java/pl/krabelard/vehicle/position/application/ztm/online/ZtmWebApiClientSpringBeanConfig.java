package pl.krabelard.vehicle.position.application.ztm.online;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZtmWebApiClientSpringBeanConfig {

	@Bean
	public ZtmApiVehiclePositionRetrievingService ztmApiVehiclePositionRetrievingService() {
		return new ZtmApiVehiclePositionRetrievingService(
			ztmWebApiClientFactory().getClient()
		);
	}

	@Bean
	public ZtmWebApiClientFactory ztmWebApiClientFactory() {
		return new ZtmWebApiClientFactory(ztmWebApiClientConfiguration());
	}

	@ConfigurationProperties(prefix = "ztm.api")
	@Bean
	public ZtmWebApiClientConfiguration ztmWebApiClientConfiguration() {
		return new ZtmWebApiClientConfiguration();
	}
}
