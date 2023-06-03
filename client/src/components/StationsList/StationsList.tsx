import styles from './StationsList.module.scss';
import { FunctionComponent } from 'react';
import { Link, useNavigate, useSearchParams } from 'react-router-dom';
import ButtonGroup, { Button } from '../ButtonGroup/ButtonGroup';
import TimetableQueryParams from '../../enums/TimetableQueryParams';

interface StationsListProps {}

const StationsList: FunctionComponent<StationsListProps> = () => {
  const navigate = useNavigate();
  const [searchParams, setSearchParams] = useSearchParams();

  const getPreviousLink = () =>
    `/direction` + `?${TimetableQueryParams.Line}=${searchParams.get(TimetableQueryParams.Line)}`;
  const getNextLink = (station: string) =>
    `/timetables` +
    `?${TimetableQueryParams.Line}=${searchParams.get(TimetableQueryParams.Line)}` +
    `&${TimetableQueryParams.Direction}=${searchParams.get(TimetableQueryParams.Direction)}` +
    `&${TimetableQueryParams.Station}=${station}`;

  const stops: Button[] = [
    'Młociny',
    'Wawrzyszew',
    'Stare Bielany',
    'Słodowiec',
    'Marymont',
    'PLac Wilsona',
    'Dworzec Gdański',
    'Ratusz Arsenał',
    'Świętokrzyska',
    'Centrum',
    'Politechnika',
    'Pole Mokotowskie',
  ].map((station) => ({
    label: station,
    action: () => {
      navigate(getNextLink(station));
    },
  }));

  return (
    <div className={`${styles.pageContainer}`}>
      <Link className={`${styles.a}`} to={getPreviousLink()}>
        <img src='/icons/go-back.svg' alt='go back' />
      </Link>
      <h2 className={`${styles.h2}`}>WSKAŻ STACJĘ</h2>
      <div className={styles.listContainer}>
        <ButtonGroup
          buttons={stops}
          color={'#1877F2'}
          maxPerRow={1}
          fontSize='21px'
          fontWeight='600'
          paddingTop='16px'
          paddingBottom='16px'
        ></ButtonGroup>
      </div>
    </div>
  );
};

export default StationsList;
