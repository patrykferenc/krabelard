package pl.krabelard.vehicle.position.application.ztm;

import java.util.List;
import lombok.RequiredArgsConstructor;
import pl.krabelard.vehicle.position.domain.model.value.Line;
import pl.krabelard.vehicle.position.domain.model.value.VehicleType;
import pl.krabelard.vehicle.position.utils.exception.KrabelardMethodNotImplementedException;

public class ZtmWebApiClient {

	public List<ZtmVehiclePositionDTO> getVehiclePositionsForLineAndVehicleType(
		Line line,
		VehicleType vehicleType
	) {
		throw new KrabelardMethodNotImplementedException();
	}

	public List<ZtmVehiclePositionDTO> getAllVehiclePositionsForVehicleType(
		VehicleType vehicleType
	) {
		throw new KrabelardMethodNotImplementedException();
	}

	@RequiredArgsConstructor
	private enum VehicleTypeQueryStringParameters {
		BUS(1),
		TRAM(2);

		private final int value;
	}
}
