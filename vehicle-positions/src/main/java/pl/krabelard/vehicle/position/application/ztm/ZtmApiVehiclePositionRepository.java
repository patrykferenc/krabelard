package pl.krabelard.vehicle.position.application.ztm;

import java.util.List;
import pl.krabelard.vehicle.position.domain.model.Vehicle;
import pl.krabelard.vehicle.position.domain.model.value.Line;
import pl.krabelard.vehicle.position.domain.model.value.VehicleType;
import pl.krabelard.vehicle.position.domain.port.VehiclePositionRepository;
import pl.krabelard.vehicle.position.utils.exception.KrabelardMethodNotImplementedException;

public class ZtmApiVehiclePositionRepository
	implements VehiclePositionRepository {

	@Override
	public List<Vehicle> getVehiclePositionsForLineAndVehicleType(
		Line line,
		VehicleType vehicleType
	) {
		throw new KrabelardMethodNotImplementedException();
	}

	@Override
	public List<Vehicle> getAllVehiclePositionsForVehicleType(
		VehicleType vehicleType
	) {
		throw new KrabelardMethodNotImplementedException();
	}
}
