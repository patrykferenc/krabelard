package pl.krabelard.timetables.line.infrastructure.controller.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record StopTimetableResponseResource(
	@JsonProperty String line,
	@JsonProperty String stop,
	@JsonProperty int number,
	@JsonProperty List<String> arrivals
) {}
