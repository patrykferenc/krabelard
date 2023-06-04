package com.krabelard.gtfsparser.infrastructure.startup;

import com.krabelard.gtfsparser.domain.entity.CalendarDate;
import com.krabelard.gtfsparser.domain.entity.Frequency;
import com.krabelard.gtfsparser.domain.entity.Route;
import com.krabelard.gtfsparser.domain.entity.Shape;
import com.krabelard.gtfsparser.domain.entity.Stop;
import com.krabelard.gtfsparser.domain.entity.StopTime;
import com.krabelard.gtfsparser.domain.entity.Trip;
import com.krabelard.gtfsparser.domain.usecase.CalendarDateRepository;
import com.krabelard.gtfsparser.domain.usecase.FrequencyRepository;
import com.krabelard.gtfsparser.domain.usecase.RouteRepository;
import com.krabelard.gtfsparser.domain.usecase.ShapeRepository;
import com.krabelard.gtfsparser.domain.usecase.StopRepository;
import com.krabelard.gtfsparser.domain.usecase.StopTimeRepository;
import com.krabelard.gtfsparser.domain.usecase.TripRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.onebusaway.gtfs.impl.GtfsDaoImpl;
import org.onebusaway.gtfs.model.ServiceCalendarDate;
import org.onebusaway.gtfs.model.ShapePoint;
import org.onebusaway.gtfs.serialization.GtfsReader;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Collection;

@Component
@RequiredArgsConstructor
public class ApplicationStartup
	implements ApplicationListener<ApplicationReadyEvent> {

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
	public void injectCalendarDatesToDatabase(Collection<ServiceCalendarDate> calendarDates) {
		for (var calendarDate : calendarDates) {
			var dbCalendarDate = CalendarDate
					.builder()
					.date(calendarDate.getDate().getAsDate())
					.exceptionType(
							CalendarDate.ExceptionType.of(calendarDate.getExceptionType())
					)
					.build();

			calendarDateRepository.save(dbCalendarDate);
		}
	}

	@Transactional
	public void injectFrequenciesToDatabase(
		Collection<org.onebusaway.gtfs.model.Frequency> frequencies
	) {
		var secondsInDay = 24 * 60 * 60;
		for (var frequency : frequencies) {
			var dbFrequency = Frequency
					.builder()
					.startTime(LocalTime.ofSecondOfDay(frequency.getStartTime() % secondsInDay))
					.endTime(LocalTime.ofSecondOfDay(frequency.getEndTime() % secondsInDay))
					.headwaySecs(frequency.getHeadwaySecs())
					.exactTimes(Frequency.ExactTimes.of(frequency.getExactTimes()))
					.build();

			frequencyRepository.save(dbFrequency);
		}
	}

	@Transactional
	public void injectRoutesToDatabase(Collection<org.onebusaway.gtfs.model.Route> routes) {
		for (var route : routes) {
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

	@Transactional
	public void injectShapesToDatabase(Collection<ShapePoint> shapes) {
		for (var shape : shapes) {
			var dbShape = Shape
					.builder()
					.gtfsId(shape.getShapeId().getId())
					.ptSequence(shape.getSequence())
					.distanceTravelled(shape.getDistTraveled())
					.ptLatitude(shape.getLat())
					.ptLongitude(shape.getLon())
					.build();

			shapeRepository.save(dbShape);
		}
	}

	@Transactional
	public void injectStopsToDatabase(Collection<org.onebusaway.gtfs.model.Stop> stops) {
		for (var stop : stops) {
			var dbStop = Stop
					.builder()
					.gtfsId(stop.getId().getId())
					.name(stop.getName())
					.latitude(stop.getLat())
					.longitude(stop.getLon())
					.locationType(Stop.LocationType.of(stop.getLocationType()))
					.zoneId(stop.getZoneId())
					.build();

			stopRepository.save(dbStop);
		}
	}

	@Transactional
	public void injectStopTimesToDatabase(Collection<org.onebusaway.gtfs.model.StopTime> stopTimes) {
		for (var stopTime : stopTimes) {
			var dbStopTime = StopTime.builder()
					.arrivalTime(LocalTime.ofSecondOfDay(stopTime.getArrivalTime()))
					.departureTime(LocalTime.ofSecondOfDay(stopTime.getDepartureTime()))
					.stopSequence(stopTime.getStopSequence())
					.pickupType(StopTime.PickupType.of(stopTime.getPickupType()))
					.dropoffType(StopTime.PickupType.of(stopTime.getDepartureTime()))
					.shapeDistTravelled(stopTime.getShapeDistTraveled())
					.build();

			stopTimeRepository.save(dbStopTime);
		}
	}

	@Transactional
	public void injectTripsToDatabase(Collection<org.onebusaway.gtfs.model.Trip> trips) {
		for (var trip : trips) {
			var dbTrip = Trip.builder()
					.tripId(trip.getId().getId())
					.headSign(trip.getTripHeadsign())
					.directionId(Trip.Direction.valueOf(trip.getDirectionId()))
					.wheelchairAccessible(Trip.Accessibility.of(trip.getWheelchairAccessible()))
					.bikesAllowed(Trip.Accessibility.of(trip.getBikesAllowed()))
					.build();

			tripRepository.save(dbTrip);
		}
	}
}
