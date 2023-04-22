package pl.krabelard.vehicle.position.domain.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;
import pl.krabelard.vehicle.position.domain.model.value.Line;
import pl.krabelard.vehicle.position.domain.model.value.Position;
import pl.krabelard.vehicle.position.domain.model.value.VehicleType;

@Value
@Builder
public class Vehicle {

	int vehicleNumber;
	VehicleType vehicleType;
	Line line;
	Position position;
	LocalDateTime timestamp;
}
