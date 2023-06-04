import React, { FunctionComponent, useState, useEffect } from 'react';
import { Link, useNavigate, useSearchParams } from 'react-router-dom';
import { OSM } from 'ol/source';
import Map from '../ol/Map/Map';
import Layers from '../ol/Layers/Layers';
import TileLayer from '../ol/Layers/TileLayer/TileLayer';
import BusLayer from '../ol/Layers/BusLayer/BusLayer';
import TimetableQueryParams from '../../enums/TimetableQueryParams';
import styles from './PositionsMap.module.scss';

interface BusPosition {
  id: number;
  latitude: number;
  longitude: number;
}

const PositionsMap: FunctionComponent = () => {
  const navigate = useNavigate();
  const [searchParams, setSearchParams] = useSearchParams();
  const [busPositions, setBusPositions] = useState<BusPosition[]>([]);

  const getPreviousLink = () => `/direction?${TimetableQueryParams.Line}=${searchParams.get(TimetableQueryParams.Line)}`;

  useEffect(() => {
    const fetchPositions = async () => {
      try {
        const response = await fetch('http://127.0.0.1:8080/vehicle-positions/buses/523');
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

  return (
    <div className={`${styles.container}`}>
      <Link className={styles.a} to={getPreviousLink()}>
        <img src='/icons/go-back.svg' alt='go back' />
      </Link>

      <div className={styles.mapContainer}>
        <Map zoom={14} center={[21.0122, 52.2297]}>
          <Layers>
            <BusLayer busPositions={busPositions} zIndex={2} />
            <TileLayer source={new OSM()} zIndex={1} />
          </Layers>
        </Map>
      </div>
    </div>
  );
};

export default PositionsMap;
