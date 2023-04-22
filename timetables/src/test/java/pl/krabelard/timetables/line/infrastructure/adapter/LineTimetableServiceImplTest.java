package pl.krabelard.timetables.line.infrastructure.adapter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.krabelard.timetables.line.domain.entity.BusStop;
import pl.krabelard.timetables.line.domain.entity.LineTimetable;
import pl.krabelard.timetables.line.infrastructure.repository.ZtmLineTimetableRepositoryImpl;

@ExtendWith(MockitoExtension.class)
public class LineTimetableServiceImplTest {

	@Mock
	private ZtmLineTimetableRepositoryImpl repository;

	@InjectMocks
	private LineTimetableServiceImpl service;

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
