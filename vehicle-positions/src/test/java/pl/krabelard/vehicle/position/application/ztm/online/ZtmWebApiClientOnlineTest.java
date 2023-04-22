package pl.krabelard.vehicle.position.application.ztm.online;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import pl.krabelard.vehicle.position.domain.model.value.Line;
import pl.krabelard.vehicle.position.domain.model.value.VehicleType;
import pl.krabelard.vehicle.position.test.IntegrationTest;

@ActiveProfiles("dev")
class ZtmWebApiClientOnlineTest extends IntegrationTest {

	@Autowired
	private ZtmWebApiClientFactory ztmWebApiClientFactory;

	private ZtmWebApiClient ztmWebApiClient;

	@BeforeEach
	void setUp() {
		configureClient();
	}

	private void configureClient() {
		ztmWebApiClient = ztmWebApiClientFactory.getClient();
	}

	@Test
	void shouldReturnListOfVehiclePositionsForGivenLineAndVehicleType_whenRequestingListOfBuses() {
		// when
		final var vehiclePositions = ztmWebApiClient.getVehiclePositionsForLineAndVehicleType(
			Line.of("thisLineCertainlyDoesNotExist"),
			VehicleType.BUS
		);

		// then
		Assertions.assertThat(vehiclePositions).isEmpty();
	}

	@Test
	void shouldReturnListOfVehiclePositionsForGivenLineAndVehicleType_whenRequestingListOfTrams() {
		// when
		final var vehiclePositions = ztmWebApiClient.getVehiclePositionsForLineAndVehicleType(
			Line.of("thisLineCertainlyDoesNotExist"),
			VehicleType.TRAM
		);

		// then
		Assertions.assertThat(vehiclePositions).isEmpty();
	}

	@Test
	void shouldReturnListOfDifferentBusPositions_whenRequestingBuses() {
		// when requesting positions for line
		final var returnedPositionDTOs = ztmWebApiClient.getAllVehiclePositionsForVehicleType(
			VehicleType.BUS
		);

		Assertions.assertThat(returnedPositionDTOs).isNotEmpty();
	}

	@Test
	void shouldReturnListOfDifferentTramPositions_whenRequestingTrams() {
		// when requesting positions for line
		final var returnedPositionDTOs = ztmWebApiClient.getAllVehiclePositionsForVehicleType(
			VehicleType.TRAM
		);

		Assertions.assertThat(returnedPositionDTOs).isNotEmpty();
	}
}
