import styles from './Timetables.module.scss';
import { FunctionComponent, useState } from 'react';
import TimeTable from '../TimeTable/TimeTable';
import { Link, useSearchParams } from 'react-router-dom';
import TimetableQueryParams from '../../enums/TimetableQueryParams';

interface TimetablesProps {}

const Timetables: FunctionComponent<TimetablesProps> = () => {
  const [searchParams, setSearchParams] = useSearchParams();
  const getPreviousLink = () =>
    `/station-list` +
    `?${TimetableQueryParams.Line}=${searchParams.get(TimetableQueryParams.Line)}` +
    `&${TimetableQueryParams.Direction}=${searchParams.get(TimetableQueryParams.Direction)}`;

  const [destination, setDestination] = useState(() => {
    const dest = searchParams.get(TimetableQueryParams.Direction);
    return dest ?? '';
  });

  return (
    <div className={`${styles.pageContainer}`}>
      <Link className={`${styles.a}`} to={getPreviousLink()}>
        <img src='/icons/go-back.svg' alt='go back' />
      </Link>
      <h2 className={styles.h2}>{searchParams.get(TimetableQueryParams.Line)}</h2>
      <div className={styles.container}>
        <TimeTable
          destination={destination}
          hour={12}
          minutes={12}
          departures={3}
          timeChange={-1}
        />
        <TimeTable
          destination={destination}
          hour={12}
          minutes={15}
          departures={7}
          timeChange={+1}
        />
        <TimeTable destination={destination} hour={12} minutes={18} departures={7} timeChange={0} />
      </div>
    </div>
  );
};

export default Timetables;
