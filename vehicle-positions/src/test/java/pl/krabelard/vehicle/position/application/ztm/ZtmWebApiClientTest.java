package pl.krabelard.vehicle.position.application.ztm;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.krabelard.vehicle.position.application.ztm.mock.MockedZtmApi;
import pl.krabelard.vehicle.position.domain.model.value.Line;
import pl.krabelard.vehicle.position.domain.model.value.VehicleType;

class ZtmWebApiClientUnitTest {

	private WireMockServer wireMockServer;

	private ZtmWebApiClient ztmWebApiClient;

	@BeforeEach
	void setUp() {
		wireMockServer = new WireMockServer();
		wireMockServer.start();

		ztmWebApiClient = new ZtmWebApiClient();
	}

	@AfterEach
	void tearDown() {
		wireMockServer.stop();
	}

	@Test
	void shouldProperlyGetVehiclePositionsAsDTOs_whenRequestingProperLine() {
		// given that api with line is available
		MockedZtmApi.mockZtmApiThatReturnsListOfBusPositions(wireMockServer);

		// when requesting positions for line
		final var returnedPositionDTOs = ztmWebApiClient.getVehiclePositionsForLineAndVehicleType(
			Line.of("179"),
			VehicleType.BUS
		);

		// then proper positions are returned
		final var expectedPositionDTOs = MockedZtmApi.getExpectedBusPositionDTOsForMockedResponse();
		Assertions
			.assertThat(returnedPositionDTOs)
			.isNotEmpty()
			.hasSize(expectedPositionDTOs.size())
			.containsExactlyInAnyOrderElementsOf(expectedPositionDTOs);
	}
}
