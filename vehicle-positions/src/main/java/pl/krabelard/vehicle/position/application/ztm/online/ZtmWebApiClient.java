package pl.krabelard.vehicle.position.application.ztm.online;

import java.net.URI;
import java.util.List;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import pl.krabelard.vehicle.position.domain.model.value.Line;
import pl.krabelard.vehicle.position.domain.model.value.VehicleType;

@RequiredArgsConstructor
class ZtmWebApiClient {

	private final ZtmWebApiClientConfiguration configuration;

	public List<ZtmVehiclePositionDTO> getVehiclePositionsForLineAndVehicleType(
		Line line,
		VehicleType vehicleType
	) {
		return executeRequestToZtmApiUsingParameters(
			buildUriWithLineAndVehicleTypeParameters(vehicleType, line)
		);
	}

	public List<ZtmVehiclePositionDTO> getAllVehiclePositionsForVehicleType(
		VehicleType vehicleType
	) {
		return executeRequestToZtmApiUsingParameters(
			buildUriWithVehicleTypeParameters(vehicleType)
		);
	}

	private List<ZtmVehiclePositionDTO> executeRequestToZtmApiUsingParameters(
		Function<UriBuilder, URI> uriBuilderFunctionWithParameters
	) {
		return WebClient
			.create(configuration.getBaseUrl())
			.get()
			.uri(uriBuilderFunctionWithParameters)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(ZtmWebApiResponseDTO.class)
			.map(ZtmWebApiResponseDTO::getResult)
			.block();
	}

	private Function<UriBuilder, URI> buildUriWithVehicleTypeParameters(
		VehicleType vehicleType
	) {
		final var vehicleTypeToGet = VehicleTypeQueryStringParameters.from(
			vehicleType
		);

		return uriBuilder ->
			uriBuilder
				.path(configuration.getPositionsResourceUrl())
				.queryParam(
					ZtmApiQueryParameters.RESOURCE_ID.value,
					configuration.getResourceId()
				)
				.queryParam(
					ZtmApiQueryParameters.APIKEY.value,
					configuration.getApiKey()
				)
				.queryParam(
					ZtmApiQueryParameters.VEHICLE_TYPE.value,
					vehicleTypeToGet.value
				)
				.build();
	}

	private Function<UriBuilder, URI> buildUriWithLineAndVehicleTypeParameters(
		VehicleType vehicleType,
		Line line
	) {
		final var vehicleTypeToGet = VehicleTypeQueryStringParameters.from(
			vehicleType
		);

		return uriBuilder ->
			uriBuilder
				.path(configuration.getPositionsResourceUrl())
				.queryParam(
					ZtmApiQueryParameters.RESOURCE_ID.value,
					configuration.getResourceId()
				)
				.queryParam(
					ZtmApiQueryParameters.APIKEY.value,
					configuration.getApiKey()
				)
				.queryParam(
					ZtmApiQueryParameters.VEHICLE_TYPE.value,
					vehicleTypeToGet.value
				)
				.queryParam(ZtmApiQueryParameters.LINE.value, line.getName())
				.build();
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

	@RequiredArgsConstructor
	private enum ZtmApiQueryParameters {
		RESOURCE_ID("resource_id"),
		APIKEY("apikey"),
		VEHICLE_TYPE("type"),
		LINE("line");

		private final String value;
	}
}
