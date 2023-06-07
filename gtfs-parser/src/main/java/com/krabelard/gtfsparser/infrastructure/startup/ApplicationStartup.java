package com.krabelard.gtfsparser.infrastructure.startup;

import com.krabelard.gtfsparser.domain.entity.*;
import com.krabelard.gtfsparser.domain.usecase.*;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationStartup
	implements ApplicationListener<ApplicationReadyEvent> {

	private static final String GTFS_ARCHIVE = "warsaw.zip";

	private final GtfsUtil gtfsUtil;
	private final CalendarDateRepository calendarDateRepository;
	private final FrequencyRepository frequencyRepository;
	private final RouteRepository routeRepository;
	private final ShapeRepository shapeRepository;
	private final StopRepository stopRepository;
	private final StopTimeRepository stopTimeRepository;
	private final TripRepository tripRepository;

	@Override
	public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
		try {
			gtfsUtil.unzip(GTFS_ARCHIVE);
		} catch (IOException e) {
			throw new GtfsArchiveProcessingException(
				String.format("Error unzipping %s", GTFS_ARCHIVE),
				e
			);
		}

		try {
			injectShapesToDatabase(gtfsUtil.parseCsv("shapes.txt"));
			injectTripsToDatabase(gtfsUtil.parseCsv("trips.txt"));
			injectStopsToDatabase(gtfsUtil.parseCsv("stops.txt"));
			injectRoutesToDatabase(gtfsUtil.parseCsv("routes.txt"));
			injectStopTimesToDatabase(gtfsUtil.parseCsv("stop_times.txt"));
			injectCalendarDatesToDatabase(gtfsUtil.parseCsv("calendar_dates.txt"));
			injectFrequenciesToDatabase(gtfsUtil.parseCsv("frequencies.txt"));
		} catch (IOException e) {
			throw new GtfsArchiveProcessingException(
				String.format(
					"Error while parsing unzipped csv files from %s",
					GTFS_ARCHIVE
				),
				e
			);
		}

		try {
			gtfsUtil.cleanUp("src/main/resources", ".txt");
		} catch (IOException e) {
			throw new GtfsArchiveProcessingException(
				String.format("Error cleaning up after %s processing", GTFS_ARCHIVE),
				e
			);
		}
	}

	@Transactional
	public void injectShapesToDatabase(List<CSVRecord> csvRecords) {
		var dbShapes = csvRecords
			.stream()
			.map(csvRecord ->
				Shape
					.builder()
					.shapeId(csvRecord.get("shape_id"))
					.ptSequence(Integer.parseInt(csvRecord.get("shape_pt_sequence")))
					.distanceTravelled(
						Double.parseDouble(csvRecord.get("shape_dist_traveled"))
					)
					.ptLatitude(Double.parseDouble(csvRecord.get("shape_pt_lat")))
					.ptLongitude(Double.parseDouble(csvRecord.get("shape_pt_lon")))
					.build()
			)
			.toList();

		shapeRepository.saveAll(dbShapes);
	}

	@Transactional
	public void injectRoutesToDatabase(List<CSVRecord> csvRecords) {
		var dbRoutes = csvRecords
			.stream()
			.map(csvRecord ->
				Route
					.builder()
					.routeId(csvRecord.get("route_id"))
					.routeShortName(csvRecord.get("route_short_name"))
					.routeLongName(csvRecord.get("route_long_name"))
					.routeType(
						Route.Type.of(Integer.parseInt(csvRecord.get("route_type")))
					)
					.routeSortOrder(Integer.parseInt(csvRecord.get("route_sort_order")))
					.build()
			)
			.toList();

		routeRepository.saveAll(dbRoutes);
	}

	@Transactional
	public void injectStopsToDatabase(List<CSVRecord> csvRecords) {
		var dbStops = csvRecords
			.stream()
			.map(csvRecord ->
				Stop
					.builder()
					.stopId(csvRecord.get("stop_id"))
					.name(csvRecord.get("stop_name"))
					.latitude(Double.parseDouble(csvRecord.get("stop_lat")))
					.longitude(Double.parseDouble(csvRecord.get("stop_lon")))
					.locationType(
						Stop.LocationType.of(
							Integer.parseInt(csvRecord.get("location_type"))
						)
					)
					.zoneId(csvRecord.get("zone_id"))
					.build()
			)
			.toList();

		stopRepository.saveAll(dbStops);
	}

	@Transactional
	public void injectTripsToDatabase(List<CSVRecord> csvRecords) {
		var dbTrips = csvRecords
			.stream()
			.map(csvRecord -> {
				var route = routeRepository
					.findById(csvRecord.get("route_id"))
					.orElseThrow();
				var shape = shapeRepository
					.findById(csvRecord.get("shape_id"))
					.orElseThrow();
				return Trip
					.builder()
					.tripId(csvRecord.get("trip_id"))
					.routeId(route)
					.headSign(csvRecord.get("trip_headsign"))
					.directionId(
						Trip.Direction.of(Integer.parseInt(csvRecord.get("direction_id")))
					)
					.shapeId(shape)
					.wheelchairAccessible(
						Trip.Accessibility.of(
							Integer.parseInt(csvRecord.get("wheelchair_accessible"))
						)
					)
					.bikesAllowed(
						Trip.Accessibility.of(
							Integer.parseInt(csvRecord.get("bikes_allowed"))
						)
					)
					.build();
			})
			.toList();

		tripRepository.saveAll(dbTrips);
	}

	@Transactional
	public void injectStopTimesToDatabase(List<CSVRecord> csvRecords) {
		var dbStopTimes = csvRecords
			.stream()
			.map(csvRecord -> {
				var trip = tripRepository
					.findById(csvRecord.get("trip_id"))
					.orElseThrow();
				var stop = stopRepository
					.findById(csvRecord.get("stop_id"))
					.orElseThrow();
				return StopTime
					.builder()
					.tripId(trip)
					.arrivalTime(LocalTime.parse(csvRecord.get("arrival_time")))
					.departureTime(LocalTime.parse(csvRecord.get("departure_time")))
					.stopId(stop)
					.stopSequence(Integer.parseInt(csvRecord.get("stop_sequence")))
					.pickupType(
						StopTime.PickupType.of(
							Integer.parseInt(csvRecord.get("pickup_type"))
						)
					)
					.dropoffType(
						StopTime.PickupType.of(
							Integer.parseInt(csvRecord.get("drop_off_type"))
						)
					)
					.shapeDistTravelled(
						Double.parseDouble(csvRecord.get("shape_dist_traveled"))
					)
					.build();
			})
			.toList();

		stopTimeRepository.saveAll(dbStopTimes);
	}

	@Transactional
	public void injectCalendarDatesToDatabase(List<CSVRecord> csvRecords) {
		var dbCalendarDates = csvRecords
			.stream()
			.map(csvRecord -> {
				var trip = tripRepository
					.findById(csvRecord.get("trip_id"))
					.orElseThrow();
				return CalendarDate
					.builder()
					.date(LocalDate.parse(csvRecord.get("date")))
					.serviceId(trip)
					.exceptionType(
						CalendarDate.ExceptionType.of(
							Integer.parseInt(csvRecord.get("exception_type"))
						)
					)
					.build();
			})
			.toList();

		calendarDateRepository.saveAll(dbCalendarDates);
	}

	@Transactional
	public void injectFrequenciesToDatabase(List<CSVRecord> csvRecords) {
		var dbFrequencies = csvRecords
			.stream()
			.map(csvRecord -> {
				var trip = tripRepository
					.findById(csvRecord.get("trip_id"))
					.orElseThrow();
				return Frequency
					.builder()
					.tripId(trip)
					.startTime(LocalTime.parse(csvRecord.get("start_time")))
					.endTime(LocalTime.parse(csvRecord.get("end_time")))
					.headwaySecs(Integer.parseInt(csvRecord.get("headway_secs")))
					.exactTimes(
						Frequency.ExactTimes.of(
							Integer.parseInt(csvRecord.get("exact_times"))
						)
					)
					.build();
			})
			.toList();

		frequencyRepository.saveAll(dbFrequencies);
	}
}
