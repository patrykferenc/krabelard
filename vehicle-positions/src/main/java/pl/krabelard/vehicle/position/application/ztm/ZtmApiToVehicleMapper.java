package pl.krabelard.vehicle.position.application.ztm;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import pl.krabelard.vehicle.position.application.ztm.online.ZtmVehiclePositionDTO;
import pl.krabelard.vehicle.position.domain.model.Vehicle;
import pl.krabelard.vehicle.position.domain.model.value.Line;
import pl.krabelard.vehicle.position.domain.model.value.Position;
import pl.krabelard.vehicle.position.domain.model.value.VehicleType;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class ZtmApiToVehicleMapper {

	static Vehicle mapToVehicle(
		ZtmVehiclePositionDTO ztmApiVehiclePosition,
		VehicleType vehicleType
	) {
		return Vehicle
			.builder()
			.vehicleNumber(ztmApiVehiclePosition.getVehicleNumber())
			.vehicleType(vehicleType)
			.line(Line.of(ztmApiVehiclePosition.getLine()))
			.position(
				Position.of(ztmApiVehiclePosition.getLatitude(), ztmApiVehiclePosition.getLongitude())
			)
			.timestamp(ztmApiVehiclePosition.getTimeOfLastUpdate())
			.build();
	}
}
