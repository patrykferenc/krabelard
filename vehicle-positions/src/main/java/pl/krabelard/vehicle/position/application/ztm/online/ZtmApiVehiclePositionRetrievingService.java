package pl.krabelard.vehicle.position.application.ztm.online;

import java.util.List;
import lombok.RequiredArgsConstructor;
import pl.krabelard.vehicle.position.domain.model.value.Line;
import pl.krabelard.vehicle.position.domain.model.value.VehicleType;

@RequiredArgsConstructor
public class ZtmApiVehiclePositionRetrievingService {

	private final ZtmWebApiClient ztmWebApiClient;

	public List<ZtmVehiclePositionDTO> getVehiclePositionsForLineAndVehicleType(
		Line line,
		VehicleType vehicleType
	) {
		return ztmWebApiClient.getVehiclePositionsForLineAndVehicleType(
			line,
			vehicleType
		);
	}

	public List<ZtmVehiclePositionDTO> getAllVehiclePositionsForVehicleType(
		VehicleType vehicleType
	) {
		return ztmWebApiClient.getAllVehiclePositionsForVehicleType(vehicleType);
	}
}
