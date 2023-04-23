package pl.krabelard.vehicle.position.application.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.krabelard.vehicle.position.domain.VehiclePositionsFacade;
import pl.krabelard.vehicle.position.domain.model.value.Line;
import pl.krabelard.vehicle.position.domain.model.value.VehicleType;

@Service
@RequiredArgsConstructor
class VehiclePositionsService {

	private final VehiclePositionsFacade vehiclePositionsFacade;

	VehiclePositionsDTO getAllTrams() {
		final var trams = vehiclePositionsFacade.getAllVehiclesOfType(VehicleType.TRAM);

		return new VehiclePositionsDTO(trams.stream().map(VehiclePositionDTO::fromDomain).toList());
	}

	VehiclePositionsDTO getTramsOnLine(String line) {
		final var tramsOnLine = vehiclePositionsFacade.getAllVehiclesOnLineThatAreOfGivenType(
			Line.of(line),
			VehicleType.TRAM
		);

		return new VehiclePositionsDTO(
			tramsOnLine.stream().map(VehiclePositionDTO::fromDomain).toList()
		);
	}

	VehiclePositionsDTO getAllBuses() {
		final var buses = vehiclePositionsFacade.getAllVehiclesOfType(VehicleType.BUS);

		return new VehiclePositionsDTO(buses.stream().map(VehiclePositionDTO::fromDomain).toList());
	}

	VehiclePositionsDTO getBusesOnLine(String line) {
		final var busesOnLine = vehiclePositionsFacade.getAllVehiclesOnLineThatAreOfGivenType(
			Line.of(line),
			VehicleType.BUS
		);

		return new VehiclePositionsDTO(
			busesOnLine.stream().map(VehiclePositionDTO::fromDomain).toList()
		);
	}
}
