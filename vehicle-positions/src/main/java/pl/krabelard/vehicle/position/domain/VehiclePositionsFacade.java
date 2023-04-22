package pl.krabelard.vehicle.position.domain;

import java.util.List;
import lombok.RequiredArgsConstructor;
import pl.krabelard.vehicle.position.domain.model.Vehicle;
import pl.krabelard.vehicle.position.domain.model.value.Line;
import pl.krabelard.vehicle.position.domain.model.value.VehicleType;
import pl.krabelard.vehicle.position.domain.port.VehiclePositionRepository;

@RequiredArgsConstructor
public class VehiclePositionsFacade {

	private final VehiclePositionRepository vehiclePositionRepository;

	public List<Vehicle> getAllVehiclesOfType(VehicleType vehicleType) {
		return vehiclePositionRepository.getAllVehiclePositionsForVehicleType(vehicleType);
	}

	public List<Vehicle> getAllVehiclesOnLineThatAreOfGivenType(Line line, VehicleType vehicleType) {
		return vehiclePositionRepository.getVehiclePositionsForLineAndVehicleType(line, vehicleType);
	}
}
