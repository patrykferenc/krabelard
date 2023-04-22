package pl.krabelard.vehicle.position.domain.model.value;

import lombok.Value;

@Value(staticConstructor = "of")
public class Position {

	double latitude;
	double longitude;
}
