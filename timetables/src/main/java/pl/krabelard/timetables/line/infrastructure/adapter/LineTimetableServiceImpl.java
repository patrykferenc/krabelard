package pl.krabelard.timetables.line.infrastructure.adapter;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.krabelard.timetables.line.domain.entity.BusStop;
import pl.krabelard.timetables.line.domain.entity.LineTimetable;
import pl.krabelard.timetables.line.domain.usecase.LineTimetableService;
import pl.krabelard.timetables.line.domain.usecase.ZtmLineTimetableRepository;

@Service
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class LineTimetableServiceImpl implements LineTimetableService {

	private final ZtmLineTimetableRepository repository;

	@Override
	public LineTimetable getFor(BusStop stop) {
		return repository.getFor(stop);
	}
}
