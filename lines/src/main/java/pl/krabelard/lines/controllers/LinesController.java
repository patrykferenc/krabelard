package pl.krabelard.lines.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.krabelard.lines.entities.direction.DirectionsDTO;
import pl.krabelard.lines.entities.line.LinesDTO;
import pl.krabelard.lines.entities.stop.StopsDTO;
import pl.krabelard.lines.services.LineService;

@RestController
@RequestMapping("/lines")
@RequiredArgsConstructor
public class LinesController {

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public LinesDTO getAllLines() {
		return LineService.getAllLinesMocked();
	}

	@GetMapping("/{line}")
	@ResponseStatus(HttpStatus.OK)
	public DirectionsDTO getDirectionsForLine(@PathVariable("line") String line) {
		return LineService.getDirectionsMocked(line);
	}

	@GetMapping("/{line}/{direction}")
	@ResponseStatus(HttpStatus.OK)
	public StopsDTO getStopsForLineAndDirection(
		@PathVariable("line") String line,
		@PathVariable("direction") String direction
	) {
		return LineService.getStopsMocked(line, direction);
	}
}
