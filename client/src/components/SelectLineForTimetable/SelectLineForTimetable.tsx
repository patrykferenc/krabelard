import styles from './SelectLineForTimetable.module.scss';
import { FunctionComponent, useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import CheckboxGroup from '../CheckboxGroup/CheckboxGroup';
import ButtonGroup, { Button } from '../ButtonGroup/ButtonGroup';
import { timetablesApi } from '../../api/timetablesApi';

interface SelectLineForTimetableProps {}

enum MeanOfTransport {
  Bus = '/icons/bus-icon.svg',
  Tram = '/icons/tram-icon.svg',
  Metro = '/icons/metro-icon.svg',
}

const SelectLineForTimetable: FunctionComponent<SelectLineForTimetableProps> = () => {
  const [showMetros, setShowMetros] = useState(true);
  const [showBuses, setShowBuses] = useState(true);
  const [showTrams, setShowTrams] = useState(true);

  const [metros, setMetros] = useState<Button[]>([]);
  const [trams, setTrams] = useState<Button[]>([]);
  const [buses, setBuses] = useState<Button[]>([]);

  useEffect(() => {
    timetablesApi.getAllLines().then(lines => {
      setMetros(lines
        .filter(line => line.vehicleType === 'METRO')
        .map(line => ({
          label: line.line,
          action: () => {navigate(`/direction?line=${line.line}`)},
          color: '#E85F5C',
        } as Button))
      );
      setTrams(lines
        .filter(line => line.vehicleType === 'TRAM')
        .map(line => ({
          label: line.line,
          action: () => {navigate(`/direction?line=${line.line}`)}
        } as Button))
      );
      setBuses(lines
        .filter(line => line.vehicleType === 'BUS')
        .map(line => ({
          label: line.line,
          action: () => {navigate(`/direction?line=${line.line}`)},
          color: '#000500',
        } as Button))
      );
    });
  }, []);

  const navigate = useNavigate();

  return (
    <div className={`${styles.container}`}>
      <Link className={`${styles.a}`} to='/'>
        <img src='/icons/go-back.svg' alt='go back' />
      </Link>
      <CheckboxGroup
        checked={true}
        isText={false}
        labels={[MeanOfTransport.Bus, MeanOfTransport.Tram, MeanOfTransport.Metro]}
        requireOneSelected={true}
        selectionChanged={(checked: boolean[]) => {
          setShowBuses(checked[0]);
          setShowTrams(checked[1]);
          setShowMetros(checked[2]);
        }}
      />
      {showMetros && (
        <div className={styles.metros}>
          {/*@ts-ignore*/}
          <ButtonGroup buttons={metros} color={'#1877F2'} maxPerRow={3} />
        </div>
      )}
      {showTrams && (
        <div className={styles.trams}>
          <ButtonGroup buttons={trams} color={'#1877F2'} maxPerRow={3} />
        </div>
      )}
      {showBuses && (
        <div className={styles.buses}>
          <ButtonGroup buttons={buses} color={'#000500'} maxPerRow={3} />
        </div>
      )}
    </div>
  );
};

export default SelectLineForTimetable;
