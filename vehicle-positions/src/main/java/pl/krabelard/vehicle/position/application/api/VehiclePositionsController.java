package pl.krabelard.vehicle.position.application.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle-positions")
@RequiredArgsConstructor
class VehiclePositionsController {

	private final VehiclePositionsService vehiclePositionsService;

	@GetMapping("/trams")
	public VehiclePositionsDTO getAllTrams() {
		return vehiclePositionsService.getAllTrams();
	}

	@GetMapping("/trams/{line}")
	public VehiclePositionsDTO getTramsOnLine(@PathVariable String line) {
		return vehiclePositionsService.getTramsOnLine(line);
	}

	@GetMapping("/buses")
	public VehiclePositionsDTO getAllBuses() {
		return vehiclePositionsService.getAllBuses();
	}

	@GetMapping("/buses/{line}")
	public VehiclePositionsDTO getBusesOnLine(@PathVariable String line) {
		return vehiclePositionsService.getBusesOnLine(line);
	}
}
