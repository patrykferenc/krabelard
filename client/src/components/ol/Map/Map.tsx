import styles from './Map.module.css';
import { View, Map as OlMap } from 'ol';
import { FunctionComponent, ReactNode, useEffect, useRef, useState } from 'react';
import MapContext from './MapContext';
import { ViewOptions } from 'ol/View';
import {fromLonLat} from 'ol/proj';
interface MapProps {
  center: number[];
  zoom: number;
  children: ReactNode;
}

const Map: FunctionComponent<MapProps> = ({ center, zoom, children }) => {
  const mapRef = useRef();
  const [map, setMap] = useState();
  useEffect(() => {
    const MapOptions = {
      view: new View({ zoom: zoom, 
        center: fromLonLat(center)
       }),
      layers: [],
      controls: [],
      overlays: [],
    };
    const mapObject = new OlMap(MapOptions);
    mapObject.setTarget(mapRef.current);
    // @ts-ignore
    setMap(mapObject);
    return () => mapObject.setTarget(undefined);
  }, []);


  return (
    <MapContext.Provider value={{ map }}>
      {/* @ts-ignore */}
      <div ref={mapRef} className={`${styles.mapContainer}`}>
        {children}
      </div>
    </MapContext.Provider>
  );
};

export default Map;
