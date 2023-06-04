import styles from './StationsList.module.scss';
import { FunctionComponent, useEffect, useState } from 'react';
import { Link, useNavigate, useSearchParams } from 'react-router-dom';
import ButtonGroup, { Button } from '../ButtonGroup/ButtonGroup';
import TimetableQueryParams from '../../enums/TimetableQueryParams';
import { timetablesApi } from '../../api/timetablesApi';

interface StationsListProps {}

const StationsList: FunctionComponent<StationsListProps> = () => {
  const navigate = useNavigate();
  const [searchParams, setSearchParams] = useSearchParams();
  const [stops, setStops] = useState<Button[]>([]);

  useEffect(() => {
    const line = searchParams.get(TimetableQueryParams.Line);
    const direction = searchParams.get(TimetableQueryParams.Direction);
    if (line && direction) {
      timetablesApi.getLineStops(line, direction).then((stops) => {
        if (stops) {
          setStops(stops.map(stop => ({
            label: stop,
            action: () => { navigate(getNextLink(stop)) }
          })))
        }
      })
    }
  }, []);

  const getPreviousLink = () =>
    `/direction` + `?${TimetableQueryParams.Line}=${searchParams.get(TimetableQueryParams.Line)}`;
  const getNextLink = (station: string) =>
    `/timetables` +
    `?${TimetableQueryParams.Line}=${searchParams.get(TimetableQueryParams.Line)}` +
    `&${TimetableQueryParams.Direction}=${searchParams.get(TimetableQueryParams.Direction)}` +
    `&${TimetableQueryParams.Station}=${station}`;

  return (
    <div className={`${styles.pageContainer}`}>
      <Link className={`${styles.a}`} to={getPreviousLink()}>
        <img src="/icons/go-back.svg" alt="go back" />
      </Link>
      <h2 className={`${styles.h2}`}>WSKAŻ STACJĘ</h2>
      <div className={styles.listContainer}>
        <ButtonGroup
          buttons={stops}
          color={'#1877F2'}
          maxPerRow={1}
          fontSize="21px"
          fontWeight="600"
          paddingTop="16px"
          paddingBottom="16px"
        ></ButtonGroup>
      </div>
    </div>
  );
};

export default StationsList;
