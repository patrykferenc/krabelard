package pl.krabelard.vehicle.position.application.api;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehiclePositionsDTO {

	private List<VehiclePositionDTO> vehiclePositions;
}
