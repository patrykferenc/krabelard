package pl.krabelard.vehicle.position.application.ztm.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import java.util.List;
import pl.krabelard.vehicle.position.application.ztm.ZtmVehiclePositionDTO;
import pl.krabelard.vehicle.position.test.TimeUtils;

public class MockedZtmApi {

	private static final String MOCKED_ZTM_API_BASE_URL =
		"api/action/busestrams_get/";

	private static final String positionsResourceId =
		"f2e5503e-927d-4ad3-9500-4ab9e55deb59";

	private static final String mockedValidApiKey =
		"123-valid-2137-api-0a0v-123-key-123";

	private static final String pathToMockedZtmApiResponses =
		"src/test/resources/ztm_api_responses/";

	private static final int busQueryParameterValue = 1;

	private static final int tramQueryParameterValue = 2;

	public static void mockZtmApiThatReturnsListOfBusPositions(
		WireMockServer wireMockServer
	) {
		wireMockServer.stubFor(
			WireMock
				.get(WireMock.urlPathMatching(MOCKED_ZTM_API_BASE_URL))
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
						.withBodyFile(
							pathToMockedZtmApiResponses + "ztm_bus_179_positions.json"
						)
				)
		);
	}

	public static List<ZtmVehiclePositionDTO> getExpectedBusPositionDTOsForMockedResponse() {
		return List.of(
			new ZtmVehiclePositionDTO(
				"179",
				9312,
				52.157444,
				21.021271,
				TimeUtils.LOCAL_DATE_TIME_NOW_MOCKED
			),
			new ZtmVehiclePositionDTO(
				"179",
				9320,
				52.153431,
				21.049162,
				TimeUtils.LOCAL_DATE_TIME_NOW_MOCKED
			),
			new ZtmVehiclePositionDTO(
				"179",
				9356,
				52.145981,
				21.036518,
				TimeUtils.LOCAL_DATE_TIME_NOW_MOCKED
			)
		);
	}
}
