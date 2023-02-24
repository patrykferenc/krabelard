package pl.krabelard.timetables.line.infrastructure.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.krabelard.timetables.line.domain.entity.BusStop;
import pl.krabelard.timetables.line.domain.usecase.LineTimetableService;
import pl.krabelard.timetables.line.infrastructure.controller.resource.StopTimetableResponseMapper;
import pl.krabelard.timetables.line.infrastructure.controller.resource.StopTimetableResponseResource;

@RestController
@RequestMapping("timetable")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class StopTimetableController {

	private final LineTimetableService busStopService;
	private final StopTimetableResponseMapper mapper;

	@GetMapping("{stop_name}/{stop_number}/{bus_line}")
	@ResponseStatus(HttpStatus.OK)
	public StopTimetableResponseResource getForBusStopWith(
		@PathVariable("stop_name") String stopName,
		@PathVariable("stop_number") int stopNumber,
		@PathVariable("bus_line") int busLine
	) {
		var bus = new BusStop(stopName, stopNumber, busLine);
		return mapper.map(busStopService.getFor(bus), bus);
	}
}
