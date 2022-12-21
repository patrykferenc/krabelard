package pl.krabelard.vehicle.position.application.ztm.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.vavr.Function1;
import java.time.LocalDateTime;
import java.util.List;
import pl.krabelard.vehicle.position.application.ztm.online.ZtmVehiclePositionDTO;

public class MockedZtmApi {

	public static final String MOCKED_ZTM_API_POSITIONS_RESOURCE_URL =
		"/api/action/busestrams_get";

	public static final String POSITIONS_RESOURCE_ID =
		"f2e5503e-927d-4ad3-9500-4ab9e55deb59";

	public static final String MOCKED_VALID_API_KEY =
		"123-valid-2137-api-0a0v-123-key-123";

	private static final int busQueryParameterValue = 1;

	private static final int tramQueryParameterValue = 2;

	public static void mockZtmApiThatReturnsUnauthorisedErrorWhenApiKeyIsInvalid(
		WireMockServer wireMockServer
	) {
		MockedZtmApi.mockZtmApiThatReturnsExactlyResponseFromResourceJson(
			wireMockServer,
			"unauthorised_response_bad_api_key.json"
		);
	}

	private static void mockZtmApiThatReturnsExactlyResponseFromResourceJson(
		WireMockServer wireMockServer,
		String jsonResponseFileLocation
	) {
		wireMockServer.stubFor(
			WireMock
				.get(
					WireMock.urlPathMatching(
						MOCKED_ZTM_API_POSITIONS_RESOURCE_URL + "?.*"
					)
				)
				.willReturn(
					WireMock
						.aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withBodyFile(jsonResponseFileLocation)
				)
		);
	}

	public static void mockZtmApiThatReturnsUnauthorisedErrorWhenApiKeyIsNotProvided(
		WireMockServer wireMockServer
	) {
		MockedZtmApi.mockZtmApiThatReturnsExactlyResponseFromResourceJson(
			wireMockServer,
			"unauthorised_response_no_api_key.json"
		);
	}

	public static Function1<WireMockServer, Void> mockZtmApiThatReturnsListOfBusPositionsFunction(
		WireMockServer wireMockServer
	) {
		MockedZtmApi.mockZtmApiThatReturnsListOfBusPositionsFromResourceJson(
			wireMockServer
		);
		return null;
	}

	private static void mockZtmApiThatReturnsListOfBusPositionsFromResourceJson(
		WireMockServer wireMockServer
	) {
		wireMockServer.stubFor(
			WireMock
				.get(WireMock.urlPathMatching(MOCKED_ZTM_API_POSITIONS_RESOURCE_URL))
				.withQueryParam("resource_id", WireMock.equalTo(POSITIONS_RESOURCE_ID))
				.withQueryParam("line", WireMock.equalTo("179"))
				.withQueryParam("apikey", WireMock.equalTo(MOCKED_VALID_API_KEY))
				.withQueryParam(
					"type",
					WireMock.equalTo(String.valueOf(busQueryParameterValue))
				)
				.willReturn(
					WireMock
						.aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withBodyFile("ztm_bus_179_positions.json")
				)
		);
	}

	public static List<ZtmVehiclePositionDTO> getExpectedBusPositionDTOsForMockedResponseOfOneLine() {
		return List.of(
			new ZtmVehiclePositionDTO(
				"179",
				9312,
				52.157444,
				21.021271,
				LocalDateTime.of(2022, 10, 25, 19, 29, 56)
			),
			new ZtmVehiclePositionDTO(
				"179",
				9320,
				52.153431,
				21.049162,
				LocalDateTime.of(2022, 10, 25, 19, 29, 58)
			),
			new ZtmVehiclePositionDTO(
				"179",
				9356,
				52.145981,
				21.036518,
				LocalDateTime.of(2022, 10, 25, 19, 29, 58)
			)
		);
	}

	public static Function1<WireMockServer, Void> mockZtmApiThatReturnsListOfTramPositionsFunction(
		WireMockServer wireMockServer
	) {
		MockedZtmApi.mockZtmApiThatReturnsListOfTramPositionsFromResourceJson(
			wireMockServer
		);
		return null;
	}

	private static void mockZtmApiThatReturnsListOfTramPositionsFromResourceJson(
		WireMockServer wireMockServer
	) {
		wireMockServer.stubFor(
			WireMock
				.get(WireMock.urlPathMatching(MOCKED_ZTM_API_POSITIONS_RESOURCE_URL))
				.withQueryParam("resource_id", WireMock.equalTo(POSITIONS_RESOURCE_ID))
				.withQueryParam("line", WireMock.equalTo("17"))
				.withQueryParam("apikey", WireMock.equalTo(MOCKED_VALID_API_KEY))
				.withQueryParam(
					"type",
					WireMock.equalTo(String.valueOf(tramQueryParameterValue))
				)
				.willReturn(
					WireMock
						.aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withBodyFile("ztm_tram_17_positions.json")
				)
		);
	}

	public static List<ZtmVehiclePositionDTO> getExpectedTramPositionDTOsForMockedResponseOfOneLine() {
		return List.of(
			new ZtmVehiclePositionDTO(
				"17",
				931,
				52.157444,
				22.021271,
				LocalDateTime.of(2022, 10, 25, 19, 29, 56)
			),
			new ZtmVehiclePositionDTO(
				"17",
				932,
				52.153431,
				22.049162,
				LocalDateTime.of(2022, 10, 25, 19, 29, 58)
			),
			new ZtmVehiclePositionDTO(
				"17",
				935,
				52.145981,
				22.036518,
				LocalDateTime.of(2022, 10, 25, 19, 29, 58)
			)
		);
	}

	public static void mockZtmApiThatReturnsEmptyListWithQueryParameter(
		WireMockServer wireMockServer,
		boolean isBusOrTram
	) {
		wireMockServer.stubFor(
			WireMock
				.get(WireMock.urlPathMatching(MOCKED_ZTM_API_POSITIONS_RESOURCE_URL))
				.withQueryParam("resource_id", WireMock.equalTo(POSITIONS_RESOURCE_ID))
				.withQueryParam("apikey", WireMock.equalTo(MOCKED_VALID_API_KEY))
				.withQueryParam(
					"type",
					WireMock.equalTo(
						String.valueOf(
							isBusOrTram ? tramQueryParameterValue : busQueryParameterValue
						)
					)
				)
				.willReturn(
					WireMock
						.aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withBodyFile("ztm_empty_positions.json")
				)
		);
	}

	public static Function1<WireMockServer, Void> mockZtmApiThatReturnsListOfDifferentBusPositionsFunction(
		WireMockServer wireMockServer
	) {
		MockedZtmApi.mockZtmApiThatReturnsListOfDifferentBusPositionsFromResourceJson(
			wireMockServer
		);
		return null;
	}

	private static void mockZtmApiThatReturnsListOfDifferentBusPositionsFromResourceJson(
		WireMockServer wireMockServer
	) {
		wireMockServer.stubFor(
			WireMock
				.get(WireMock.urlPathMatching(MOCKED_ZTM_API_POSITIONS_RESOURCE_URL))
				.withQueryParam("resource_id", WireMock.equalTo(POSITIONS_RESOURCE_ID))
				.withQueryParam("apikey", WireMock.equalTo(MOCKED_VALID_API_KEY))
				.withQueryParam(
					"type",
					WireMock.equalTo(String.valueOf(busQueryParameterValue))
				)
				.willReturn(
					WireMock
						.aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withBodyFile("ztm_bus_179_504_positions.json")
				)
		);
	}

	public static List<ZtmVehiclePositionDTO> getExpectedBusPositionDTOsForMockedResponseOfDifferentLines() {
		return List.of(
			new ZtmVehiclePositionDTO(
				"179",
				9312,
				52.157444,
				21.021271,
				LocalDateTime.of(2022, 10, 25, 19, 29, 56)
			),
			new ZtmVehiclePositionDTO(
				"179",
				9320,
				52.153431,
				21.049162,
				LocalDateTime.of(2022, 10, 25, 19, 29, 58)
			),
			new ZtmVehiclePositionDTO(
				"179",
				9356,
				52.145981,
				21.036518,
				LocalDateTime.of(2022, 10, 25, 19, 29, 58)
			),
			new ZtmVehiclePositionDTO(
				"504",
				935,
				52.45981,
				21.36518,
				LocalDateTime.of(2022, 10, 25, 19, 29, 28)
			)
		);
	}

	public static Function1<WireMockServer, Void> mockZtmApiThatReturnsListOfDifferentTramPositionsFunction(
		WireMockServer wireMockServer
	) {
		MockedZtmApi.mockZtmApiThatReturnsListOfDifferentTramPositionsFromResourceJson(
			wireMockServer
		);
		return null;
	}

	private static void mockZtmApiThatReturnsListOfDifferentTramPositionsFromResourceJson(
		WireMockServer wireMockServer
	) {
		wireMockServer.stubFor(
			WireMock
				.get(WireMock.urlPathMatching(MOCKED_ZTM_API_POSITIONS_RESOURCE_URL))
				.withQueryParam("resource_id", WireMock.equalTo(POSITIONS_RESOURCE_ID))
				.withQueryParam("apikey", WireMock.equalTo(MOCKED_VALID_API_KEY))
				.withQueryParam(
					"type",
					WireMock.equalTo(String.valueOf(tramQueryParameterValue))
				)
				.willReturn(
					WireMock
						.aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withBodyFile("ztm_tram_17_18_positions.json")
				)
		);
	}

	public static List<ZtmVehiclePositionDTO> getExpectedTramPositionDTOsForMockedResponseOfDifferentLines() {
		return List.of(
			new ZtmVehiclePositionDTO(
				"17",
				931,
				52.157444,
				22.021271,
				LocalDateTime.of(2022, 10, 25, 19, 29, 56)
			),
			new ZtmVehiclePositionDTO(
				"17",
				932,
				52.153431,
				22.049162,
				LocalDateTime.of(2022, 10, 25, 19, 29, 58)
			),
			new ZtmVehiclePositionDTO(
				"17",
				935,
				52.145981,
				22.036518,
				LocalDateTime.of(2022, 10, 25, 19, 29, 58)
			),
			new ZtmVehiclePositionDTO(
				"18",
				93,
				52.45981,
				22.36518,
				LocalDateTime.of(2022, 10, 25, 19, 29, 49)
			),
			new ZtmVehiclePositionDTO(
				"18",
				96,
				52.9999999,
				22.736518,
				LocalDateTime.of(2022, 10, 25, 19, 29, 8)
			)
		);
	}
}
