package pl.krabelard.vehicle.position.application.ztm.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
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

	public static void mockZtmApiThatReturnsListOfBusPositions(
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

	public static List<ZtmVehiclePositionDTO> getExpectedBusPositionDTOsForMockedResponse() {
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
}
