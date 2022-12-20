package pl.krabelard.vehicle.position.application.ztm.online;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "from")
class ZtmWebApiClientConfiguration {

	@NonNull
	private final String baseUrl;

	@NonNull
	private final String positionsResourceUrl;

	@NonNull
	private final String apiKey;

	@NonNull
	private final String resourceId;
}
