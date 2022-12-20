package pl.krabelard.vehicle.position.application.ztm.online;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class ZtmWebApiClientTestFactory implements ZtmWebApiClientFactory {

	private final String wireMockBaseUrlToUseAsZtmWebApiBaseUrl;

	private final String mockedPositionsResourceUrl;

	private final String mockedResourceId;

	private final String mockedApiKey;

	@Override
	public ZtmWebApiClient getClient() {
		final var testConfiguration = ZtmWebApiClientConfiguration.from(
			wireMockBaseUrlToUseAsZtmWebApiBaseUrl,
			mockedPositionsResourceUrl,
			mockedApiKey,
			mockedResourceId
		);

		return new ZtmWebApiClient(testConfiguration);
	}
}
