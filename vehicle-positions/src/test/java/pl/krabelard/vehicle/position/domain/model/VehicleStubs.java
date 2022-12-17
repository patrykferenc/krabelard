package pl.krabelard.vehicle.position.domain.model;

import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import pl.krabelard.vehicle.position.domain.model.value.Line;
import pl.krabelard.vehicle.position.domain.model.value.Position;
import pl.krabelard.vehicle.position.domain.model.value.VehicleType;
import pl.krabelard.vehicle.position.test.TimeUtils;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class VehicleStubs {

	public static Vehicle getSampleVehicleWithNumberTypeAndLine(
		int vehicleNumber,
		Line line,
		VehicleType vehicleType
	) {
		return Vehicle
			.builder()
			.vehicleNumber(vehicleNumber)
			.vehicleType(vehicleType)
			.line(line)
			.position(Position.of(51, 22))
			.timestamp(TimeUtils.LOCAL_DATE_TIME_NOW_MOCKED)
			.build();
	}

	public static List<Vehicle> getSampleVehiclesListWithLineAndVehicleType(
		Line line,
		VehicleType vehicleType
	) {
		return List.of(
			getSampleVehicleWithNumberTypeAndLine(1, line, vehicleType),
			getSampleVehicleWithNumberTypeAndLine(2, line, vehicleType),
			getSampleVehicleWithNumberTypeAndLine(3, line, vehicleType)
		);
	}
}
