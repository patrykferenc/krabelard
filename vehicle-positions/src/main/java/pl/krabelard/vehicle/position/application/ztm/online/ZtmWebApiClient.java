package pl.krabelard.vehicle.position.application.ztm.online;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import pl.krabelard.vehicle.position.domain.model.value.Line;
import pl.krabelard.vehicle.position.domain.model.value.VehicleType;
import pl.krabelard.vehicle.position.utils.exception.KrabelardMethodNotImplementedException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
class ZtmWebApiClient {

	private final ZtmWebApiClientConfiguration configuration;

	public List<ZtmVehiclePositionDTO> getVehiclePositionsForLineAndVehicleType(
		Line line,
		VehicleType vehicleType
	) {
		final var vehicleTypeToGet = VehicleTypeQueryStringParameters.from(
			vehicleType
		);

		return WebClient
			.create(configuration.getBaseUrl())
			.get()
			.uri(uriBuilder ->
				uriBuilder
					.path(configuration.getPositionsResourceUrl())
					.queryParam("resource_id", configuration.getResourceId())
					.queryParam("apikey", configuration.getApiKey())
					.queryParam("type", vehicleTypeToGet.value)
					.queryParam("line", line.getName())
					.build()
			)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(ZtmWebApiResponseDTO.class)
			.map(ZtmWebApiResponseDTO::getResult)
			.block();
	}

	public Flux<ZtmVehiclePositionDTO> getAllVehiclePositionsForVehicleType(
		VehicleType vehicleType
	) {
		throw new KrabelardMethodNotImplementedException();
	}

	@RequiredArgsConstructor
	private enum VehicleTypeQueryStringParameters {
		BUS(1),
		TRAM(2);

		private final int value;

		static VehicleTypeQueryStringParameters from(VehicleType vehicleType) {
			return switch (vehicleType) {
				case BUS -> BUS;
				case TRAM -> TRAM;
			};
		}
	}
}
