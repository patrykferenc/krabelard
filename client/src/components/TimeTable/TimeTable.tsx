import styles from './TimeTable.module.scss';
import { FunctionComponent } from 'react';

interface TimeTableProps {
  destination: string;
  hour: number;
  minutes: number;
  departures: number;
  timeChange: number;
}

function getDeparturesClass(currentDepartures: number): string {
  if (currentDepartures < 5) {
    return 'low-departures';
  } else if (currentDepartures < 10) {
    return 'medium-departures';
  } else {
    return 'high-departures';
  }
}

const TimeTable: FunctionComponent<TimeTableProps> = ({
  destination,
  hour,
  minutes,
  departures,
  timeChange,
}) => {
  return (
    <div className={styles.table}>
      <div className={styles.white_part}>
        <h3 className={styles.destination}>{destination}</h3>
        <div className={styles.timeData}>
          <span className={styles.time}>
            {hour}:{minutes}
          </span>
          <span
            className={`${timeChange === 0 ? styles.none : styles.changeTime}`}
            style={{ color: `${timeChange < 0 ? '#34D098' : '#E85F5C'}` }}
          >{`${timeChange > 0 ? '+' : ''}${timeChange}`}</span>
        </div>
      </div>
      <div className={`${styles.color_part} ${getDeparturesClass(departures)}`}>
        <span className={styles.za}>za</span>
        <span className={styles.departure}>{departures}</span>
        <span className={styles.min}>min</span>
      </div>
    </div>
  );
};

export default TimeTable;
