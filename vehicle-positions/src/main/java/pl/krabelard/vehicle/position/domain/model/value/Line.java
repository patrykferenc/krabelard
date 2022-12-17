package pl.krabelard.vehicle.position.domain.model.value;

import lombok.Value;

@Value(staticConstructor = "of")
public class Line {

	String name;
}
