package pl.krabelard.vehicle.position.application.ztm.online;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZtmWebApiClientSpringBeanConfig {

	@Bean
	public ZtmApiVehiclePositionRetrievingService ztmApiVehiclePositionRetrievingService(
		ZtmWebApiClient ztmWebApiClient
	) {
		return new ZtmApiVehiclePositionRetrievingService(ztmWebApiClient);
	}

	@Bean
	public ZtmWebApiClient ztmWebApiClient(
		ZtmWebApiClientFactory ztmWebApiClientFactory
	) {
		return ztmWebApiClientFactory.getClient();
	}

	@Bean
	public ZtmWebApiClientFactory ztmWebApiClientFactory(
		ZtmWebApiClientConfiguration ztmWebApiClientConfiguration
	) {
		return new ZtmWebApiClientFactory(ztmWebApiClientConfiguration);
	}

	@ConfigurationProperties(prefix = "ztm.api")
	@Bean
	public ZtmWebApiClientConfiguration ztmWebApiClientConfiguration() {
		return new ZtmWebApiClientConfiguration();
	}
}
