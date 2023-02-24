package pl.krabelard.timetables.line.infrastructure.repository.resource;

import java.time.LocalTime;
import java.util.List;

public interface ZtmLineTimetableMapper {
	List<LocalTime> map(ZtmLineTimetableApiResource resource);
}
