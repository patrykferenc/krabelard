import { FunctionComponent, useContext, useEffect } from 'react';
import MapContext from '../../Map/MapContext';
import OlTileLayer from 'ol/layer/Tile';

interface TileLayerProps {
  source: any;
  zIndex: number;
}

const TileLayer: FunctionComponent<TileLayerProps> = ({ source, zIndex = 0 }) => {
  // @ts-ignore
  const { map } = useContext(MapContext);
  useEffect(() => {
    if (!map) return;
    const layer = new OlTileLayer({ source, zIndex });
    map.addLayer(layer);
  }, [map, source, zIndex]);
  return null;
};

export default TileLayer;
