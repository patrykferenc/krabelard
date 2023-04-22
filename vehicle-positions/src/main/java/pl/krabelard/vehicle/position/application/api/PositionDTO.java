package pl.krabelard.vehicle.position.application.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.krabelard.vehicle.position.domain.model.value.Position;

@Data
@AllArgsConstructor
public class PositionDTO {

	double latitude;
	double longitude;

	static PositionDTO fromDomain(Position position) {
		return new PositionDTO(position.getLatitude(), position.getLongitude());
	}
}
