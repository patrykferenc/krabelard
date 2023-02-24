package pl.krabelard.timetables.line.infrastructure.adapter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.krabelard.timetables.line.domain.entity.BusStop;
import pl.krabelard.timetables.line.domain.entity.LineTimetable;
import pl.krabelard.timetables.line.domain.usecase.LineTimetableService;
import pl.krabelard.timetables.line.domain.usecase.ZtmLineTimetableRepository;

public class LineTimetableServiceImplTest {

	private ZtmLineTimetableRepository repository;
	private LineTimetableService service;

	@BeforeEach
	void setup() {
		repository = mock(ZtmLineTimetableRepository.class);
		service = new LineTimetableServiceImpl(repository);
	}

	@Test
	void whenGetForCalled_shouldCallRepositoryGetForMethod() {
		//given
		var stop = new BusStop("name", 1, 23);
		when(repository.getFor(stop)).thenReturn(new LineTimetable(List.of(LocalTime.NOON)));
		//when
		service.getFor(stop);
		//then
		verify(repository, times(1)).getFor(any());
	}
}
