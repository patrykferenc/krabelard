import { krapi } from './krapi';

const timetablesClient = krapi('/timetables');

export const timetableApi = {
  getAllLines() {
    //TODO: add when GTFS feeds are ready
    //get all lines from routes table
    //ex.
    //SELECT DISTINCT route_id
    // FROM routes;

    async function getAllLines(): Promise<object> {
      // TODO: connect to GTFS database and fetch stop names
      const lines = await fetchLines();

      // TODO: format stop names as JSON object
      const jsonStops = {
        lines: lines
      };

      // Return JSON object
      return jsonStops;
    }
    async function fetchLines(): Promise<Array<string>> {
      // TODO: connect to GTFS database and fetch arrival times
      const lines = ['M1', 'M2', '1', '2', '3', '4', '6', '7', '9', '11', '13', '15', '17', '20', '102', '103'];

      return lines;
    }

    getAllLines().then(result => console.log(result));
    return getAllLines();
  },

  getDirectionsForLine(line: string) {
    //TODO: add when GTFS feeds are ready
    //get directions from routes table - substring
    //ex.
    //SELECT route_long_name
    // FROM routes
    // WHERE route_id = 'your_route_id_here';
    async function getDirectionsForLine(line: string): Promise<object> {
      // TODO: connect to GTFS database and fetch stop names
      const directions = await fetchDirections(line);

      // TODO: format stop names as JSON object
      const jsonStops = {
        line: line,
        directions: directions
      };

      // Return JSON object
      return jsonStops;
    }
    async function fetchDirections(line: string): Promise<Array<string>> {
      // TODO: connect to GTFS database and fetch arrival times
      const directions = ['Młociny', 'Kabaty'];

      return directions;
    }
    // Example usage
    line = 'M1';
    getDirectionsForLine(line).then(result => console.log(result));
    return getDirectionsForLine(line);
  },

  getAllStopsForLineAndDirection(line: string, direction: string) {
    //TODO: add when GTFS feeds are ready
    //get stop names for line and direction - multiple query: trips -> stop_times -> stops
    //ex.
    //SELECT stop_id, stop_name FROM stops WHERE stop_id IN (
    //   SELECT DISTINCT stop_id FROM stop_times WHERE trip_id IN (
    //     SELECT trip_id FROM trips WHERE route_id = <route_id>));

    async function getAllStopsForLineAndDirection(line: string, direction: string): Promise<object> {
      // TODO: connect to GTFS database and fetch stop names
      const stops = await fetchStopNames(line, direction);

      // TODO: format stop names as JSON object
      const jsonStops = {
        line: line,
        direction: direction,
        stops: stops
      };

      // Return JSON object
      return jsonStops;
    }

    // Fetch stop names from GTFS database
    async function fetchStopNames(line: string, direction: string): Promise<Array<string>> {
      // TODO: connect to GTFS database and fetch stop names
      const stops = [
        'Stokłosy',
        'Ursynów',
        'Służew',
        'Wilanowska',
        'Wierzbno',
        'Racławicka',
        'Pole Mokotowskie',
        'Politechnika',
        'Centrum',
        'Świętokrzyska',
        'Ratusz Arsenał',
        'Dworzec Gdański',
        'Plac Wilsona',
        'Wawelowe',
        'Stare Bielany',
        'Słodowiec',
        'Marymont',
        'Wawrzyszew',
        'Młociny'
      ];

      return stops;
    }

    // Example usage
    line = 'M1';
    direction = 'Młociny';
    getAllStopsForLineAndDirection(line, direction).then(result => console.log(result));

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
    async function getArrivalsForStopAndLine(line: string, direction: string, stopName: string): Promise<object> {
      // TODO: connect to GTFS database and fetch arrival times
      const arrivals = await fetchArrivalTimes(line, direction, stopName);

      // TODO: format arrival times as JSON object
      const jsonArrivals = {
        line: line,
        direction: direction,
        stopName: stopName,
        arrivals: arrivals
      };

      // Return JSON object
      return jsonArrivals;
    }

    // Fetch arrival times from GTFS database
    async function fetchArrivalTimes(line: string, direction: string, stopName: string): Promise<Array<string>> {
      // TODO: connect to GTFS database and fetch arrival times
      const arrivals = ['09:35:00', '10:15:00', '11:00:00'];

      return arrivals;
    }
    // Example usage
    line = 'M1';
    direction = 'Młociny';
    stopName = 'Centrum';
    getArrivalsForStopAndLine(line, direction, stopName).then(result => console.log(result));

    return getArrivalsForStopAndLine(line, direction, stopName);
  }
};
