import { krapi } from './krapi';

const timetablesClient = krapi('/timetables');

export const timetablesApi = {
  getAllLines: async () => [
    {
      vehicleType: 'TRAM',
      line: '22',
    },
    {
      vehicleType: 'TRAM',
      line: '78',
    },
    {
      vehicleType: 'TRAM',
      line: '15',
    },
    {
      vehicleType: 'TRAM',
      line: '4',
    },
    {
      vehicleType: 'BUS',
      line: '509',
    },
    {
      vehicleType: 'BUS',
      line: '179',
    },
    {
      vehicleType: 'BUS',
      line: '504',
    },
    {
      vehicleType: 'BUS',
      line: '218',
    },
    {
      vehicleType: 'BUS',
      line: '319',
    },
    {
      vehicleType: 'BUS',
      line: '508',
    },
    {
      vehicleType: 'BUS',
      line: '166',
    },
    {
      vehicleType: 'BUS',
      line: '136',
    },
    {
      vehicleType: 'BUS',
      line: '138',
    },
    {
      vehicleType: 'BUS',
      line: '318',
    },
    {
      vehicleType: 'TRAIN',
      line: 'S2',
    },
    {
      vehicleType: 'TRAIN',
      line: 'S1',
    },
    {
      vehicleType: 'TRAIN',
      line: 'S8',
    },
    {
      vehicleType: 'METRO',
      line: 'M1',
    },
    {
      vehicleType: 'METRO',
      line: 'M2',
    },
  ],
  getLineDirections: async (line: string) => {
    return {
      '22': {directionFrom: 'Kabaty', directionTo: 'Plac Wilsona'},
      '78': {directionFrom: 'Dworzec Wschodni', directionTo: 'Żerań FSO'},
      '15': {directionFrom: 'Plac Wilsona', directionTo: 'Gocławek Wschodni'},
      '4': {directionFrom: 'Dworzec Wileński', directionTo: 'Zajezdnia Wola'},
      '509': {directionFrom: 'Chomiczówka', directionTo: 'Dworzec Wileński'},
      '179': {directionFrom: 'Twoja stara', directionTo: 'Twój stary'},
    }[line]
  },
  getLineStops: async (line: string, direction: string) => {
    return [
      'Młociny',
      'Wawrzyszew',
      'Stare Bielany',
      'Słodowiec',
      'Marymont',
      'PLac Wilsona',
      'Dworzec Gdański',
      'Ratusz Arsenał',
      'Świętokrzyska',
      'Centrum',
      'Politechnika',
      'Pole Mokotowskie',
      'Raclawicka',
      'Wierzbno',
      'Wilanowska',
      'Służew',
      'Ursynów',
      'Stokłosy',
      'Imielin',
      'Natolin',
      'Kabaty'
    ]
  },
  getLineAtStopDepartureTimesWithDelay: async (line: string, stop: string, direction: string) => {
    return [
      {
        departureTime: Date.now() + 1000000,
        arrivalTime: Date.now() - 50,
        delay: 0,
      },
      {
        departureTime: Date.now() - 100,
        arrivalTime: Date.now() - 100,
        delay: 5,
      },
      {
        departureTime: Date.now() - 400,
        arrivalTime: Date.now() - 400,
        delay: 10,
      }
    ]
  }
};
