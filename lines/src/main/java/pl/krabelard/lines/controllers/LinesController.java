package pl.krabelard.lines.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.krabelard.lines.entities.direction.DirectionsDTO;
import pl.krabelard.lines.entities.line.LinesDTO;
import pl.krabelard.lines.entities.stop.StopsDTO;
import pl.krabelard.lines.services.LineService;

@Slf4j
@RestController
@RequestMapping("/lines")
@RequiredArgsConstructor
public class LinesController {

	private final LineService lineService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public LinesDTO getAllLines() {
		return lineService.getAllLinesMocked();
	}

	@GetMapping("/{line}")
	@ResponseStatus(HttpStatus.OK)
	public DirectionsDTO getDirectionsForLine(@PathVariable("line") String line) {
		return lineService.getDirectionsMocked(line);
	}

	@GetMapping("/{line}/{direction}")
	@ResponseStatus(HttpStatus.OK)
	public StopsDTO getStopsForLineAndDirection(
		@PathVariable("line") String line,
		@PathVariable("direction") String direction
	) {
		return lineService.getStopsMocked(line, direction);
	}
}
