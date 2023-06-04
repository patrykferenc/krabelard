package com.krabelard.gtfsparser.infrastructure.startup;

import com.krabelard.gtfsparser.domain.entity.*;
import com.krabelard.gtfsparser.domain.usecase.*;
import jakarta.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.onebusaway.gtfs.impl.GtfsDaoImpl;
import org.onebusaway.gtfs.model.ServiceCalendarDate;
import org.onebusaway.gtfs.model.ShapePoint;
import org.onebusaway.gtfs.serialization.GtfsReader;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationStartup
	implements ApplicationListener<ApplicationReadyEvent> {

	private static final int SECONDS_IN_DAY = 60 * 60 * 24;
	private final CalendarDateRepository calendarDateRepository;
	private final FrequencyRepository frequencyRepository;
	private final RouteRepository routeRepository;
	private final ShapeRepository shapeRepository;
	private final StopRepository stopRepository;
	private final StopTimeRepository stopTimeRepository;
	private final TripRepository tripRepository;

	@Override
	public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
		var reader = new GtfsReader();
		var store = new GtfsDaoImpl();
		var input = new File("src/test/resources/warsaw.zip");
		try {
			reader.setInputLocation(input);
			reader.setEntityStore(store);
			reader.run();

			injectCalendarDatesToDatabase(store.getAllCalendarDates());
			injectFrequenciesToDatabase(store.getAllFrequencies());
			injectRoutesToDatabase(store.getAllRoutes());
			injectShapesToDatabase(store.getAllShapePoints());
			injectStopsToDatabase(store.getAllStops());
			injectStopTimesToDatabase(store.getAllStopTimes());
			injectTripsToDatabase(store.getAllTrips());
		} catch (IOException e) {
			throw new GtfsDbLoadingException(
				"Could not load the gtfs database into postgres db."
			);
		}
	}

	@Transactional
	public void injectCalendarDatesToDatabase(
		Collection<ServiceCalendarDate> calendarDates
	) {
		var dbCalendarDates = calendarDates
			.stream()
			.map(calendarDate ->
				CalendarDate
					.builder()
					.date(calendarDate.getDate().getAsDate())
					.exceptionType(
						CalendarDate.ExceptionType.of(calendarDate.getExceptionType())
					)
					.build()
			)
			.toList();

		calendarDateRepository.saveAll(dbCalendarDates);
	}

	@Transactional
	public void injectFrequenciesToDatabase(
		Collection<org.onebusaway.gtfs.model.Frequency> frequencies
	) {
		var dbFrequencies = frequencies
			.stream()
			.map(frequency ->
				Frequency
					.builder()
					.startTime(
						LocalTime.ofSecondOfDay(frequency.getStartTime() % SECONDS_IN_DAY)
					)
					.endTime(
						LocalTime.ofSecondOfDay(frequency.getEndTime() % SECONDS_IN_DAY)
					)
					.headwaySecs(frequency.getHeadwaySecs())
					.exactTimes(Frequency.ExactTimes.of(frequency.getExactTimes()))
					.build()
			)
			.toList();
		frequencyRepository.saveAll(dbFrequencies);
	}

	@Transactional
	public void injectRoutesToDatabase(
		Collection<org.onebusaway.gtfs.model.Route> routes
	) {
		var dbRoutes = routes
			.stream()
			.map(route ->
				Route
					.builder()
					.routeId(route.getId().getId())
					.longRouteName(route.getLongName())
					.routeShortName(route.getShortName())
					.routeSortOrder(route.getSortOrder())
					.build()
			)
			.toList();
		routeRepository.saveAll(dbRoutes);
	}

	@Transactional
	public void injectShapesToDatabase(Collection<ShapePoint> shapes) {
		var dbShapes = shapes
			.stream()
			.map(shape ->
				Shape
					.builder()
					.shapeId(shape.getShapeId().getId())
					.ptSequence(shape.getSequence())
					.distanceTravelled(shape.getDistTraveled())
					.ptLatitude(shape.getLat())
					.ptLongitude(shape.getLon())
					.build()
			)
			.toList();

		shapeRepository.saveAll(dbShapes);
	}

	@Transactional
	public void injectStopsToDatabase(
		Collection<org.onebusaway.gtfs.model.Stop> stops
	) {
		var dbStops = stops
			.stream()
			.map(stop ->
				Stop
					.builder()
					.stopId(stop.getId().getId())
					.name(stop.getName())
					.latitude(stop.getLat())
					.longitude(stop.getLon())
					.locationType(Stop.LocationType.of(stop.getLocationType()))
					.zoneId(stop.getZoneId())
					.build()
			)
			.toList();

		stopRepository.saveAll(dbStops);
	}

	@Transactional
	public void injectStopTimesToDatabase(
		Collection<org.onebusaway.gtfs.model.StopTime> stopTimes
	) {
		var dbStopTimes = stopTimes
			.stream()
			.map(stopTime ->
				StopTime
					.builder()
					.arrivalTime(LocalTime.ofSecondOfDay(stopTime.getArrivalTime()))
					.departureTime(LocalTime.ofSecondOfDay(stopTime.getDepartureTime()))
					.stopSequence(stopTime.getStopSequence())
					.pickupType(StopTime.PickupType.of(stopTime.getPickupType()))
					.dropoffType(StopTime.PickupType.of(stopTime.getDepartureTime()))
					.shapeDistTravelled(stopTime.getShapeDistTraveled())
					.build()
			)
			.toList();

		stopTimeRepository.saveAll(dbStopTimes);
	}

	@Transactional
	public void injectTripsToDatabase(
		Collection<org.onebusaway.gtfs.model.Trip> trips
	) {
		var dbTrips = trips
			.stream()
			.map(trip ->
				Trip
					.builder()
					.tripId(trip.getId().getId())
					.headSign(trip.getTripHeadsign())
					.directionId(Trip.Direction.valueOf(trip.getDirectionId()))
					.wheelchairAccessible(
						Trip.Accessibility.of(trip.getWheelchairAccessible())
					)
					.bikesAllowed(Trip.Accessibility.of(trip.getBikesAllowed()))
					.build()
			)
			.toList();

		tripRepository.saveAll(dbTrips);
	}
}
