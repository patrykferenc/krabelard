import styles from './PositionsMap.module.scss';
import CheckboxGroup from '../CheckboxGroup/CheckboxGroup';
import Map from '../ol/Map/Map';
import Input from '../Input/Input';
import PrimaryButton from '../PrimaryButton/PrimaryButton';
import Layers from '../ol/Layers/Layers';
import TileLayer from '../ol/Layers/TileLayer/TileLayer';
import { OSM } from 'ol/source';
import { FunctionComponent, useState } from 'react';
import { Link, useNavigate, useSearchParams } from 'react-router-dom';
import BusLayer from '../ol/Layers/BusLayer/BusLayer';
import TimetableQueryParams from '../../enums/TimetableQueryParams';


const PositionsMap: FunctionComponent = () => {

    const navigate = useNavigate();
  const [searchParams, setSearchParams] = useSearchParams();

     const getPreviousLink = () =>
    `/direction` + `?${TimetableQueryParams.Line}=${searchParams.get(TimetableQueryParams.Line)}`;
 
  return (
    <div className={`${styles.container}`}>
      <Link className={`${styles.a}`} to={getPreviousLink()}>
        <img src='/icons/go-back.svg' alt='go back' />
      </Link>
     
      <div className={`${styles.mapContainer}`}>
        <Map zoom={14} center={
          [
            21.0122,
            52.2297
          ]
        }>
          <Layers>
             <BusLayer busPositions={
              [
                {
                  id: 1,
                  latitude: 52.2297,
                  longitude: 21.0122,
                },
                {
                  id: 2,
                  latitude: 52.1297,
                  longitude: 21.0122,
                }
              ]
            }
            zIndex={2}
            />
            <TileLayer source={new OSM()} zIndex={1} />
           
          </Layers>
        </Map>

      </div>
      
    </div>
  );
};

export default PositionsMap;
