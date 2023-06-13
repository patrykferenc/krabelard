import React, { useEffect, useContext } from 'react';
import { Vector as VectorLayer } from 'ol/layer';
import { Vector as VectorSource } from 'ol/source';
import { Style, Circle, Fill, Stroke } from 'ol/style';
import { fromLonLat } from 'ol/proj';
import { Feature } from 'ol';
import { Point } from 'ol/geom';
import MapContext from '../../Map/MapContext';

interface MyPositionProps {
  currentPosition?: {
    latitude: number;
    longitude: number;
  } | null;
  zIndex: number;
}

const MyPosition: React.FC<MyPositionProps> = ({ currentPosition, zIndex }) => {
    //@ts-ignore
  const { map } = useContext(MapContext);

  useEffect(() => {
    if (!map || !currentPosition) return;

    const myPositionLayer = new VectorLayer({
      source: new VectorSource(),
      zIndex: zIndex,
    });

    map.addLayer(myPositionLayer);

    return () => {
      map.removeLayer(myPositionLayer);
    };
  }, [map, zIndex]);

  useEffect(() => {
    if (!map || !currentPosition) return;

    const myPositionSource = map
      .getLayers()
      .getArray()
      //@ts-ignore
      .find((layer) => layer.get('name') === 'MyPosition')
      ?.getSource() as VectorSource<Point>;

    if (!myPositionSource) return;

    myPositionSource.clear();

    const { latitude, longitude } = currentPosition;
    const myPositionCoordinates = fromLonLat([longitude, latitude]);
    const myPositionPoint = new Point(myPositionCoordinates);
    const myPositionFeature = new Feature({
      geometry: myPositionPoint,
    });

    const myPositionIconStyle = new Style({
      image: new Circle({
        radius: 8,
        fill: new Fill({
          color: 'blue',
        }),
        stroke: new Stroke({
          color: 'blue',
          width: 2,
        }),
      }),
    });

    myPositionFeature.setStyle(myPositionIconStyle);
    myPositionSource.addFeature(myPositionFeature);
  }, [currentPosition, map]);

  return null;
};

export default MyPosition;
