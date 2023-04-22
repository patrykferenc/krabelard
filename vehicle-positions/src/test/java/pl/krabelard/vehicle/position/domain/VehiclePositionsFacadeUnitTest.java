package pl.krabelard.vehicle.position.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.krabelard.vehicle.position.domain.model.Vehicle;
import pl.krabelard.vehicle.position.domain.model.VehicleStubs;
import pl.krabelard.vehicle.position.domain.model.value.Line;
import pl.krabelard.vehicle.position.domain.model.value.VehicleType;
import pl.krabelard.vehicle.position.domain.port.VehiclePositionRepository;

@ExtendWith(MockitoExtension.class)
class VehiclePositionsFacadeUnitTest {

	@Mock
	private VehiclePositionRepository vehiclePositionRepositoryMocked;

	private VehiclePositionsFacade vehiclePositionsFacadeUnderTest;

	@BeforeEach
	void setUpFacade() {
		vehiclePositionsFacadeUnderTest =
			new VehiclePositionsFacade(vehiclePositionRepositoryMocked);
	}

	@ParameterizedTest
	@EnumSource(VehicleType.class)
	void shouldReturnEmptyVehicleList_whenNoVehiclesAreFound(
		VehicleType vehicleTypeToTest
	) {
		// given there are no vehicles in the repository
		setUpRepositoryMockToReturnEmptyLists(vehicleTypeToTest);

		// when
		final var vehiclesReturnedFromRepository = vehiclePositionsFacadeUnderTest.getAllVehiclesOfType(
			vehicleTypeToTest
		);

		// then
		Assertions.assertThat(vehiclesReturnedFromRepository).isEmpty();
	}

	private void setUpRepositoryMockToReturnEmptyLists(
		VehicleType vehicleTypeToTest
	) {
		Mockito
			.when(
				vehiclePositionRepositoryMocked.getAllVehiclePositionsForVehicleType(
					vehicleTypeToTest
				)
			)
			.thenReturn(List.of());
	}

	@ParameterizedTest
	@EnumSource(VehicleType.class)
	void shouldReturnListOfDistinctVehicles_whenThereAreVehiclesInRepository(
		VehicleType vehicleTypeToTest
	) {
		// given there are vehicles in the repository
		final var expectedLine = Line.of("179");
		final var expectedVehicles = VehicleStubs.getSampleVehiclesListWithLineAndVehicleType(
			expectedLine,
			vehicleTypeToTest
		);
		setUpRepositoryMockToReturnVehicles(
			expectedVehicles,
			expectedLine,
			vehicleTypeToTest
		);

		// when
		final var vehiclesReturnedFromRepository = vehiclePositionsFacadeUnderTest.getAllVehiclesOnLineThatAreOfGivenType(
			expectedLine,
			vehicleTypeToTest
		);

		// then
		Assertions
			.assertThat(vehiclesReturnedFromRepository)
			.isNotEmpty()
			.hasSize(expectedVehicles.size())
			.containsExactlyInAnyOrderElementsOf(expectedVehicles);
	}

	private void setUpRepositoryMockToReturnVehicles(
		List<Vehicle> vehiclesToReturn,
		Line line,
		VehicleType vehicleTypeToTest
	) {
		Mockito
			.when(
				vehiclePositionRepositoryMocked.getVehiclePositionsForLineAndVehicleType(
					line,
					vehicleTypeToTest
				)
			)
			.thenReturn(vehiclesToReturn);
	}
}
