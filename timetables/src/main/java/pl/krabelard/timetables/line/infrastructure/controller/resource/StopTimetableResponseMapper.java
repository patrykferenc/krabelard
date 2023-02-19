package pl.krabelard.timetables.line.infrastructure.controller.resource;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.krabelard.timetables.line.domain.entity.BusStop;
import pl.krabelard.timetables.line.domain.entity.LineTimetable;

@Mapper(componentModel = "spring")
public interface StopTimetableResponseMapper {
	@Mapping(target = "stop", source = "stop.name")
	StopTimetableResponseResource map(LineTimetable timetable, BusStop stop);
}
