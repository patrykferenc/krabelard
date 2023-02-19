package pl.krabelard.timetables.line.infrastructure.repository.resource;

import java.util.List;

public interface ZtmLineTimetableMapper {
	List<String> map(ZtmLineTimetableApiResource resource);
}
