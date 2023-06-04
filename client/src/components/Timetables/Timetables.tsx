import styles from './Timetables.module.scss';
import { FunctionComponent, useEffect, useState } from 'react';
import TimeTable from '../TimeTable/TimeTable';
import { Link, useSearchParams } from 'react-router-dom';
import TimetableQueryParams from '../../enums/TimetableQueryParams';
import { timetablesApi } from '../../api/timetablesApi';

interface TimetablesProps {}

interface Time {
  date: Date;
  delay: number;
}

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

  const [times, setTimes] = useState<Time[]>([]);

  useEffect(() => {
    const line = searchParams.get(TimetableQueryParams.Line);
    const direction = searchParams.get(TimetableQueryParams.Direction);
    const stop = searchParams.get(TimetableQueryParams.Station);
    if (line && direction && stop) {
      timetablesApi.getLineAtStopDepartureTimesWithDelay(line, stop, direction).then(times => {
        setTimes(times.map<Time>(time => {
          return {
            date: new Date(time.departureTime),
            delay: time.delay
          }
        }));
      });
    }
  }, []);

  return (
    <div className={`${styles.pageContainer}`}>
      <Link className={`${styles.a}`} to={getPreviousLink()}>
        <img src="/icons/go-back.svg" alt="go back" />
      </Link>
      <h2 className={styles.h2}>{searchParams.get(TimetableQueryParams.Line)}</h2>
      <div className={styles.listContainer}>
        {
          times.map((time, index) => {
            const hours = time.date.getHours();
            const minutes = time.date.getMinutes();
            return (
              <TimeTable
                destination={destination}
                hour={hours.toString().length === 1 ? `0${hours}` : hours.toString()}
                minutes={minutes.toString().length === 1 ? `0${minutes}` : minutes.toString()}
                departures={time.date.getTime() - new Date().getTime() > 0 ? Math.floor((time.date.getTime() - new Date().getTime()) / 60000) : 0}
                timeChange={time.delay}
                key={index}
              />
            )
          })
        }
      </div>
    </div>
  );
};

export default Timetables;
