import styles from './SelectLineForTimetable.module.scss';
import { FunctionComponent, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import CheckboxGroup from '../CheckboxGroup/CheckboxGroup';
import ButtonGroup, { Button } from '../ButtonGroup/ButtonGroup';

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
  const navigate = useNavigate();

  const metros: Button[] = [
    {
      label: 'M1',
      action: () => {
        navigate(`/direction?line=M1`);
      },
    },
    {
      label: 'M2',
      action: () => {
        navigate('/direction?line=M2');
      },
      color: '#E85F5C',
    },
  ];

  const trams: Button[] = ['78', '4', '15', '35', '9'].map((station) => ({
    label: station,
    action: () => {
      navigate(`/direction?line=${station}`);
    },
  }));

  const buses: Button[] = ['179', '189', '185', '192', '503', '504', '209'].map((station) => ({
    label: station,
    action: () => {
      navigate(`/direction?line=${station}`);
    },
  }));

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
