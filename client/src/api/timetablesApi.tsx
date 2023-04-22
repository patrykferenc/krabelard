import { krapi } from './krapi';

const timetablesClient = krapi('/timetables');

export const timetableApi = {
  getAllLines() {
    //TODO: add when GTFS feeds are ready
    //get all lines from routes table
    return null;
  },

  getDirectionsForLine(line: string) {
    //TODO: add when GTFS feeds are ready
    //get directions from routes table - substring
    return null;
  },

  getAllStopsForLineAndDirection(line: string, direction: string) {
    //TODO: add when GTFS feeds are ready
    //get
    return null;
  },

  getArrivalsForStopAndLine(line: string, stopName: string, stopNumber: string) {
    return timetablesClient.get(`/${stopName}/${stopNumber}/${line}`);
  }
};
