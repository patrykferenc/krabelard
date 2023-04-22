import { krapi } from "./krapi";

const timetablesClient = krapi("/timetables");

export const timetableApi = {
  getAllLines() {
    //TODO: add when GTFS feeds are ready
    //get all lines from routes table
    //ex.
    //SELECT DISTINCT route_id
    // FROM routes;
    return null;
  },

  getDirectionsForLine(line: string) {
    //TODO: add when GTFS feeds are ready
    //get directions from routes table - substring
    //ex.
    //SELECT route_long_name
    // FROM routes
    // WHERE route_id = 'your_route_id_here';
    return null;
  },

  getAllStopsForLineAndDirection(line: string, direction: string) {
    //TODO: add when GTFS feeds are ready
    //get stop names for line and direction - multiple query: trips -> stop_times -> stops
    //ex.
    //SELECT stop_id, stop_name FROM stops WHERE stop_id IN (
    //   SELECT DISTINCT stop_id FROM stop_times WHERE trip_id IN (
    //     SELECT trip_id FROM trips WHERE route_id = <route_id>));
    return null;
  },

  getArrivalsForStopAndLine(line: string, direction: string, stopName: string) {
    //TODO: add when GTFS feeds are ready
    //get arrival times from stop_times by filtering through:
    // trip_id -> stop_id -> then get arrival time
    //ex.
    //SELECT stop_times.arrival_time
    // FROM trips
    // JOIN routes ON trips.route_id = routes.route_id
    // JOIN stop_times ON trips.trip_id = stop_times.trip_id
    // JOIN stops ON stop_times.stop_id = stops.stop_id
    // WHERE routes.route_long_name = 'your_line_here'
    // AND trips.direction_id = 'your_direction_here'
    // AND stops.stop_name = 'your_stop_name_here';
    return null;
  },
};
