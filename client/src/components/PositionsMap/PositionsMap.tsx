import React, { FunctionComponent, useState, useEffect } from 'react';
import { Link, useNavigate, useSearchParams } from 'react-router-dom';
import { OSM } from 'ol/source';
import Map from '../ol/Map/Map';
import Layers from '../ol/Layers/Layers';
import TileLayer from '../ol/Layers/TileLayer/TileLayer';
import BusLayer from '../ol/Layers/BusLayer/BusLayer';
import TimetableQueryParams from '../../enums/TimetableQueryParams';
import styles from './PositionsMap.module.scss';
import MyPosition from '../ol/Layers/MyPosition/MyPosition';

interface BusPosition {
  id: number;
  latitude: number;
  longitude: number;
}

const PositionsMap: FunctionComponent = () => {
  const navigate = useNavigate();
  const [searchParams, setSearchParams] = useSearchParams();
  const [busPositions, setBusPositions] = useState<BusPosition[]>([]);
  const [currentPosition, setCurrentPosition] = useState<{
    latitude: number;
    longitude: number;
  } | null>(null);

  const getPreviousLink = () =>
    `/direction?${TimetableQueryParams.Line}=${searchParams.get(TimetableQueryParams.Line)}`;

  const getLine = () => searchParams.get(TimetableQueryParams.Line);

  const typeOfTransport: string = getLine()?.length === 3 ? 'buses' : 'trams';

  const getCurrentPosition = async() => {
    if (navigator.geolocation) {
      try {
        navigator.geolocation.getCurrentPosition(
          (position) => {
            setCurrentPosition({
              latitude: position.coords.latitude,
              longitude: position.coords.longitude,
            });
          },
          (error) => {
            console.error('Error getting current position:', error);
          }
        );
      } catch (error) {
        console.error('Error accessing geolocation:', error);
      }
      console.log(currentPosition);
    } else {
      console.error('Geolocation is not supported by this browser.');
    }
  };

  useEffect(() => {
    const fetchPositions = async () => {
      try {
        const response = await fetch(
          'http://127.0.0.1:8080/vehicle-positions/' + typeOfTransport + getLine()
        );
        const data = await response.json();
        if (Array.isArray(data.vehiclePositions)) {
          const positions = data.vehiclePositions.map((position: any) => ({
            id: position.id,
            latitude: position.position.latitude,
            longitude: position.position.longitude,
          })) as BusPosition[];
          setBusPositions(positions);
        }
        console.log('Positions fetched:', data);
      } catch (error) {
        console.error('Error fetching positions:', error);
      }
    };

    const intervalId = setInterval(fetchPositions, 5000);

    return () => {
      clearInterval(intervalId);
    };
  }, []);

  useEffect(() => {
    getCurrentPosition();

    const intervalId2 = setInterval(getCurrentPosition, 5000);
    return () => {
      clearInterval(intervalId2);
    };
  }, []);

  return (
    <div className={`${styles.container}`}>
      <Link className={styles.a} to={getPreviousLink()}>
        <img src='/icons/go-back.svg' alt='go back' />
      </Link>

      <div className={styles.mapContainer}>
        <Map
          zoom={14}
          center={currentPosition ? [currentPosition.longitude, currentPosition.latitude] : [0, 0]}
        >
          <Layers>
            <MyPosition currentPosition={currentPosition} zIndex={2} />

            <BusLayer busPositions={busPositions} zIndex={2} />
            <TileLayer source={new OSM()} zIndex={1} />
          </Layers>
        </Map>
      </div>
    </div>
  );
};

export default PositionsMap;
