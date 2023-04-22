package pl.krabelard.vehicle.position.application.ztm.online;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ZtmWebApiClientFactory {

	private final ZtmWebApiClientConfiguration ztmWebApiClientConfiguration;

	public ZtmWebApiClient getClient() {
		return new ZtmWebApiClient(ztmWebApiClientConfiguration);
	}
}
