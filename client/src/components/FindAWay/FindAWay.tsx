import styles from './FindAWay.module.scss';
import CheckboxGroup from '../CheckboxGroup/CheckboxGroup';
import Map from '../ol/Map/Map';
import Input from '../Input/Input';
import PrimaryButton from '../PrimaryButton/PrimaryButton';
import Layers from '../ol/Layers/Layers';
import TileLayer from '../ol/Layers/TileLayer/TileLayer';
import { OSM } from 'ol/source';
import { FunctionComponent, useState } from 'react';
import { Link } from 'react-router-dom';

const FindAWay: FunctionComponent = () => {
  const [isFullscreen, setIsFullscreen] = useState(false);
  const toggle = () => {
    setIsFullscreen(!isFullscreen);
  };
  return (
    <div className={`${styles.container}`}>
      <Link className={`${styles.a}`} to='/'>
        <img src='/icons/go-back.svg' alt='go back' />
      </Link>
      {!isFullscreen && (
        <CheckboxGroup
          checked={true}
          isText={false}
          labels={['/icons/bus.svg', '/icons/tram.svg', '/icons/metro.svg']}
          requireOneSelected={true}
          selectionChanged={(checked: boolean[]) => {
            // TODO
          }}
        />
      )}
      <div className={`${styles.mapContainer}`}>
        <Map zoom={4} center={[52.2297, 21.0122]}>
          <Layers>
            <TileLayer source={new OSM()} zIndex={1} />
          </Layers>
        </Map>
        <button className={`${styles.button}`} onClick={toggle}>
          <img src={isFullscreen ? '/icons/arrow-up.svg' : '/icons/arrow-down.svg'} alt='toggle' />
        </button>
      </div>
      {!isFullscreen && (
        <>
          {/* @ts-ignore */}
          <Input
            label='Z'
            placeholder='TWOJA LOKALIZACJA'
            labelWidth={'2rem'}
            onChange={(currentLocation) => {
              // TODO
            }}
          />
          {/* @ts-ignore */}
          <Input
            label='DO'
            placeholder='TWÓJ CEL'
            labelWidth={'2rem'}
            onChange={(destination) => {
              // TODO
            }}
          />
        </>
      )}
      <PrimaryButton text='JEDŹ' onClick={() => {}}></PrimaryButton>
    </div>
  );
};

export default FindAWay;
