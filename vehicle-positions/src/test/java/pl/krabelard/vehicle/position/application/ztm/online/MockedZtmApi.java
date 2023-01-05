package pl.krabelard.vehicle.position.application.ztm.online;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import java.time.LocalDateTime;
import java.util.List;

public class MockedZtmApi {

	private final String mockedZtmApiPositionsResourceUrl;

	private final String positionsResourceId;

	private final String mockedValidApiKey;

	private static final int busQueryParameterValue = 1;

	private static final int tramQueryParameterValue = 2;

	private final WireMockServer wireMockServer;

	public MockedZtmApi(
		ZtmWebApiClientConfiguration ztmWebApiClientConfiguration
	) {
		this.wireMockServer =
			new WireMockServer(WireMockConfiguration.options().port(5080)); // TODO#KRB-86: add base url
		this.mockedZtmApiPositionsResourceUrl =
			ztmWebApiClientConfiguration.getPositionsResourceUrl();
		this.positionsResourceId = ztmWebApiClientConfiguration.getResourceId();
		this.mockedValidApiKey = ztmWebApiClientConfiguration.getApiKey();
	}

	public void start() {
		this.wireMockServer.start();
	}

	public void stop() {
		this.wireMockServer.stop();
	}

	public void thatReturnsUnauthorisedErrorWhenApiKeyIsInvalid() {
		mockAnyPathAndReturnJsonFromFile("unauthorised_response_bad_api_key.json");
	}

	private void mockAnyPathAndReturnJsonFromFile(
		String jsonResponseFileLocation
	) {
		wireMockServer.stubFor(
			WireMock
				.get(WireMock.urlPathMatching(mockedZtmApiPositionsResourceUrl + "?.*"))
				.willReturn(
					WireMock
						.aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withBodyFile(jsonResponseFileLocation)
				)
		);
	}

	public void thatReturnsUnauthorisedErrorWhenApiKeyIsNotProvided() {
		mockAnyPathAndReturnJsonFromFile("unauthorised_response_no_api_key.json");
	}

	public void thatReturnsListOfBusPositionsOfOnlyOneLine() {
		wireMockServer.stubFor(
			WireMock
				.get(WireMock.urlPathMatching(mockedZtmApiPositionsResourceUrl))
				.withQueryParam("resource_id", WireMock.equalTo(positionsResourceId))
				.withQueryParam("line", WireMock.equalTo("179"))
				.withQueryParam("apikey", WireMock.equalTo(mockedValidApiKey))
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

	public static List<ZtmVehiclePositionDTO> getExpectedBusPositionsForMockedResponseOfOnlyOneLine() {
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

	public void thatReturnsListOfTramPositionsOfOnlyOneLine() {
		wireMockServer.stubFor(
			WireMock
				.get(WireMock.urlPathMatching(mockedZtmApiPositionsResourceUrl))
				.withQueryParam("resource_id", WireMock.equalTo(positionsResourceId))
				.withQueryParam("line", WireMock.equalTo("17"))
				.withQueryParam("apikey", WireMock.equalTo(mockedValidApiKey))
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

	public static List<ZtmVehiclePositionDTO> getExpectedTramPositionsForMockedResponseOfOnlyOneLine() {
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

	public void thatReturnsEmptyListWithQueryParameterOfBusesOrTrams(
		boolean isBusOrTram
	) {
		wireMockServer.stubFor(
			WireMock
				.get(WireMock.urlPathMatching(mockedZtmApiPositionsResourceUrl))
				.withQueryParam("resource_id", WireMock.equalTo(positionsResourceId))
				.withQueryParam("apikey", WireMock.equalTo(mockedValidApiKey))
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

	public void thatReturnsListOfDifferentBusPositions() {
		wireMockServer.stubFor(
			WireMock
				.get(WireMock.urlPathMatching(mockedZtmApiPositionsResourceUrl))
				.withQueryParam("resource_id", WireMock.equalTo(positionsResourceId))
				.withQueryParam("apikey", WireMock.equalTo(mockedValidApiKey))
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

	public static List<ZtmVehiclePositionDTO> getExpectedBusPositionsForMockedResponseOfDifferentLines() {
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

	public void thatReturnsListOfDifferentTramPositions() {
		wireMockServer.stubFor(
			WireMock
				.get(WireMock.urlPathMatching(mockedZtmApiPositionsResourceUrl))
				.withQueryParam("resource_id", WireMock.equalTo(positionsResourceId))
				.withQueryParam("apikey", WireMock.equalTo(mockedValidApiKey))
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

	public static List<ZtmVehiclePositionDTO> getExpectedTramPositionsForMockedResponseOfDifferentLines() {
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
