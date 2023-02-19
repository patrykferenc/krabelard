package pl.krabelard.timetables.line.infrastructure.repository.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ZtmLineTimetableApiResource(@JsonProperty List<Value> result) {
	public record Value(@JsonProperty List<KeyValue> values) {}

	public record KeyValue(
		@JsonProperty String key,
		@JsonProperty String value
	) {}
}
