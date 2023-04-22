package pl.krabelard.vehicle.position.application.ztm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.krabelard.vehicle.position.application.ztm.online.ZtmApiVehiclePositionRetrievingService;
import pl.krabelard.vehicle.position.domain.port.VehiclePositionRepository;

@Disabled("TODO: #KRB-86 Disabled during development - to be removed")
@ExtendWith(MockitoExtension.class)
class ZtmApiVehiclePositionRepositoryUnitTest {

	@Mock
	private ZtmApiVehiclePositionRetrievingService ztmApiVehiclePositionRetrievingService;

	private VehiclePositionRepository ztmApiVehiclePositionRepository;

	@BeforeEach
	void setUp() {
		ztmApiVehiclePositionRepository =
			new ZtmApiVehiclePositionRepository(
				ztmApiVehiclePositionRetrievingService
			);
	}

	@Test
	void getVehiclePositionsForLineAndVehicleType_returnsVehicles_whenExpectingCorrectDTOs() {
		Assertions.fail("Not implemented yet");
	}

	@Test
	void getAllVehiclePositionsForLineVehicleType_returnsEmptyList_whenNoVehicleDTOsFound() {
		Assertions.fail("Not implemented yet");
	}

	@Test
	void getVehiclePositionsForVehicleType_returnsVehicles_whenExpectingCorrectDTOs() {
		Assertions.fail("Not implemented yet");
	}

	@Test
	void getAllVehiclePositionsForVehicleType_returnsEmptyList_whenNoVehicleDTOsFound() {
		Assertions.fail("Not implemented yet");
	}
}
