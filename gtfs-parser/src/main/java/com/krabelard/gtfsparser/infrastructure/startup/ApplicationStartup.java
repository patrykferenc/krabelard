package com.krabelard.gtfsparser.infrastructure.startup;

import com.krabelard.gtfsparser.domain.entity.Route;
import com.krabelard.gtfsparser.domain.usecase.RouteRepository;
import com.krabelard.gtfsparser.infrastructure.handler.GtfsEntityHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.onebusaway.gtfs.impl.GtfsDaoImpl;
import org.onebusaway.gtfs.serialization.GtfsReader;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ApplicationStartup
	implements ApplicationListener<ApplicationReadyEvent> {

	private final RouteRepository routeRepository;

	@Override
	public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
		var reader = new GtfsReader();
		var store = new GtfsDaoImpl();
		var handler = new GtfsEntityHandler();
		var input = new File("src/test/resources/warsaw.zip");
		try {
			reader.setInputLocation(input);
			reader.setEntityStore(store);
			reader.addEntityHandler(handler);
			reader.run();

			for (org.onebusaway.gtfs.model.Route route : store.getAllRoutes()) {
				addRoutesToDatabase(route);
			}
		} catch (IOException e) {
			throw new RuntimeException(
				"Could not load the gtfs database into postgres db."
			);
		}
	}

	@Transactional
	public void addRoutesToDatabase(org.onebusaway.gtfs.model.Route route) {
		var dbRoute = Route
			.builder()
			.routeId(route.getId().getId())
			.longRouteName(route.getLongName())
			.routeShortName(route.getShortName())
			.routeType(Route.RouteType.of(route.getType()))
			.routeSortOrder(route.getSortOrder())
			.build();

		routeRepository.save(dbRoute);
	}
}
