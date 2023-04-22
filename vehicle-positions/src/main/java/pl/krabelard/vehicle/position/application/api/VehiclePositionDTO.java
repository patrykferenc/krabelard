package pl.krabelard.vehicle.position.application.api;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import pl.krabelard.vehicle.position.domain.model.Vehicle;

@Data
@AllArgsConstructor
public class VehiclePositionDTO {

	private int id;
	private String vehicleType;
	private String line;
	private PositionDTO position;
	private LocalDateTime timestamp;

	static VehiclePositionDTO fromDomain(Vehicle vehicle) {
		return new VehiclePositionDTO(
			vehicle.getVehicleNumber(),
			vehicle.getVehicleType().name(),
			vehicle.getLine().getName(),
			PositionDTO.fromDomain(vehicle.getPosition()),
			vehicle.getTimestamp()
		);
	}
}
