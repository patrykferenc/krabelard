import { krapi } from './krapi';

const postionsClient = krapi('/vehicle-positions');

export const positionsApi = {
  getAllTramsPositions() {
    return postionsClient.get(`/trams`);
  },

  getAllTramsPositionsOnLine(line: string) {
    return postionsClient.get(`/trams/${line}`);
  },

  getAllBusesPositions() {
    return postionsClient.get(`/buses`);
  },

  getAllBusesPositionsOnLine(line: string) {
    return postionsClient.get(`/buses/${line}`);
  },
};
