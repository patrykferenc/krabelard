package pl.krabelard.vehicle.position.application.ztm.online;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@NoArgsConstructor
class ZtmWebApiClientConfiguration {

	@NonNull
	private String baseUrl;

	@NonNull
	private String positionsResourceUrl;

	@NonNull
	private String apiKey;

	@NonNull
	private String resourceId;
}
