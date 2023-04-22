package pl.krabelard.vehicle.position.domain.port;

import java.util.List;
import pl.krabelard.vehicle.position.domain.model.Vehicle;
import pl.krabelard.vehicle.position.domain.model.value.Line;
import pl.krabelard.vehicle.position.domain.model.value.VehicleType;

public interface VehiclePositionRepository {
	List<Vehicle> getVehiclePositionsForLineAndVehicleType(
		Line line,
		VehicleType vehicleType
	);

	List<Vehicle> getAllVehiclePositionsForVehicleType(VehicleType vehicleType);
}
