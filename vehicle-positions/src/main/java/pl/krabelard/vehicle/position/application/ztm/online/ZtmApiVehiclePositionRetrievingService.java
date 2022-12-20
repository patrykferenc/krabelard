package pl.krabelard.vehicle.position.application.ztm.online;

import java.util.List;
import lombok.RequiredArgsConstructor;
import pl.krabelard.vehicle.position.domain.model.Vehicle;
import pl.krabelard.vehicle.position.domain.model.value.Line;
import pl.krabelard.vehicle.position.domain.model.value.VehicleType;
import pl.krabelard.vehicle.position.utils.exception.KrabelardMethodNotImplementedException;

@RequiredArgsConstructor
public class ZtmApiVehiclePositionRetrievingService {

	private final ZtmWebApiClient ztmWebApiClient;

	public List<Vehicle> getVehiclePositionsForLineAndVehicleType(
		Line line,
		VehicleType vehicleType
	) {
		throw new KrabelardMethodNotImplementedException();
	}

	public List<Vehicle> getAllVehiclePositionsForVehicleType(
		VehicleType vehicleType
	) {
		throw new KrabelardMethodNotImplementedException();
	}
}
