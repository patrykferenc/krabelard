package pl.krabelard.vehicle.position.domain;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.krabelard.vehicle.position.test.IntegrationTest;

class VehiclePositionsFacadeIntegrationTest extends IntegrationTest {

	@Autowired
	private VehiclePositionsFacade vehiclePositionsFacade;

	@Test
	void shouldGetAllVehiclesOfType() {
		final var expectedVehicles = List.of();

		Assertions.fail("Not implemented yet");
	}
}
