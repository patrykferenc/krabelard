import React, { useEffect, useContext } from 'react';
import { Vector as VectorLayer } from 'ol/layer';
import { Vector as VectorSource } from 'ol/source';
import { Style, Circle, Fill, Stroke } from 'ol/style';
import { fromLonLat } from 'ol/proj';
import { Feature } from 'ol';
import { Point } from 'ol/geom';
import MapContext from '../../Map/MapContext';

interface BusLayerProps {
  busPositions: BusPosition[];
  zIndex: number;
}

interface BusPosition {
  id: number;
  latitude: number;
  longitude: number;
}

const BusLayer: React.FC<BusLayerProps> = ({ busPositions, zIndex }) => {
     // @ts-ignore
  const { map } = useContext(MapContext);

  useEffect(() => {
    if (!map) return;

    const busLayer = new VectorLayer({
      source: new VectorSource(),
      zIndex: zIndex,
    });

    map.addLayer(busLayer);

    return () => {
      map.removeLayer(busLayer);
    };
  }, [map, zIndex]);

  useEffect(() => {
    if (!map) return;

    const busSource = map
      .getLayers()
      .getArray()
       // @ts-ignore
      .find((layer) => layer instanceof VectorLayer) as VectorLayer<VectorSource<Point>>;

    if (!busSource) return;

    busSource?.getSource()?.clear();

    busPositions.forEach((busPosition) => {
      const { id, latitude, longitude } = busPosition;
      const busCoordinates = fromLonLat([longitude, latitude]);
      const busPoint = new Point(busCoordinates);
      const busFeature = new Feature({
        geometry: busPoint,
        id: id,
      });

      const busIconStyle = new Style({
        image: new Circle({
          radius: 8,
          fill: new Fill({
            color: 'red',
          }),
          stroke: new Stroke({
            color: 'red',
            width: 2,
          }),
        }),
      });

      busFeature.setStyle(busIconStyle);
      busSource?.getSource()?.addFeature(busFeature);
    });
  }, [busPositions, map]);

  return null;
};

export default BusLayer;
