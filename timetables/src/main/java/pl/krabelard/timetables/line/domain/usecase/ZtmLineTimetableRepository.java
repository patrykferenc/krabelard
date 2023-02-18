package pl.krabelard.timetables.line.domain.usecase;

import pl.krabelard.timetables.line.domain.entity.BusStop;
import pl.krabelard.timetables.line.domain.entity.LineTimetable;

public interface ZtmLineTimetableRepository {
	LineTimetable getFor(BusStop stop);
}
