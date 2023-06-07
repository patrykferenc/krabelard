package com.krabelard.gtfsparser.infrastructure.startup;

import com.krabelard.gtfsparser.domain.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	private final CalendarDateRepository calendarDateRepository;
	private final FrequencyRepository frequencyRepository;
	private final RouteRepository routeRepository;
	private final ShapeRepository shapeRepository;
	private final StopRepository stopRepository;
	private final StopTimeRepository stopTimeRepository;
	private final TripRepository tripRepository;

	@Override
	public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
	}
}
