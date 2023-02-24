package pl.krabelard.timetables.line.infrastructure.repository.resource;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Predicate;

public class ZtmLineTimetableMapperImpl implements ZtmLineTimetableMapper {

	private static final String TIME_MAP_VALUE = "czas";

	@Override
	public List<LocalTime> map(ZtmLineTimetableApiResource resource) {
		return resource
			.result()
			.stream()
			.map(x ->
				parse(
					debracket(
						x
							.values()
							.stream()
							.filter(byKey())
							.map(ZtmLineTimetableApiResource.KeyValue::value)
							.toList()
							.toString()
					)
				)
			)
			.toList();
	}

	private String debracket(String input) {
		return input.substring(input.indexOf("[") + 1, input.indexOf("]"));
	}

	private LocalTime parse(String time) {
		return time.equals("24:00:00") ? LocalTime.MIDNIGHT : LocalTime.parse(time);
	}

	private Predicate<ZtmLineTimetableApiResource.KeyValue> byKey() {
		return x -> x.key().equals(TIME_MAP_VALUE);
	}
}
