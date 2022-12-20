package pl.krabelard.vehicle.position.application.ztm.online;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class ZtmWebApiClientProductionFactory implements ZtmWebApiClientFactory {

	@Override
	public ZtmWebApiClient getClient() {
		final var configuration = ZtmWebApiClientConfiguration.from(
			System.getenv("ZTM_API_BASE_URL"),
			System.getenv("ZTM_API_POSITIONS_RESOURCE_URL"),
			System.getenv("ZTM_API_KEY"),
			System.getenv("ZTM_API_RESOURCE_ID")
		);

		return new ZtmWebApiClient(configuration);
	}
}
