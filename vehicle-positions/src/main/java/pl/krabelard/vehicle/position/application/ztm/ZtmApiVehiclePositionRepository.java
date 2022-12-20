package pl.krabelard.vehicle.position.application.ztm;

import java.util.List;
import lombok.RequiredArgsConstructor;
import pl.krabelard.vehicle.position.application.ztm.online.ZtmApiVehiclePositionRetrievingService;
import pl.krabelard.vehicle.position.domain.model.Vehicle;
import pl.krabelard.vehicle.position.domain.model.value.Line;
import pl.krabelard.vehicle.position.domain.model.value.VehicleType;
import pl.krabelard.vehicle.position.domain.port.VehiclePositionRepository;

@RequiredArgsConstructor
public class ZtmApiVehiclePositionRepository
	implements VehiclePositionRepository {

	private final ZtmApiVehiclePositionRetrievingService ztmApiVehiclePositionRetrievingService;

	@Override
	public List<Vehicle> getVehiclePositionsForLineAndVehicleType(
		Line line,
		VehicleType vehicleType
	) {
		return ztmApiVehiclePositionRetrievingService.getVehiclePositionsForLineAndVehicleType(
			line,
			vehicleType
		);
	}

	@Override
	public List<Vehicle> getAllVehiclePositionsForVehicleType(
		VehicleType vehicleType
	) {
		return ztmApiVehiclePositionRetrievingService.getAllVehiclePositionsForVehicleType(
			vehicleType
		);
	}
}
