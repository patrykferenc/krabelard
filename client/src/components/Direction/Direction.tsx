import styles from './Direction.module.scss';
import { FunctionComponent, useEffect, useState } from 'react';
import { Link, useNavigate, useSearchParams } from 'react-router-dom';
import TimetableQueryParams from '../../enums/TimetableQueryParams';
import { timetablesApi } from '../../api/timetablesApi';

interface DirectionListProps {}

const Direction: FunctionComponent<DirectionListProps> = () => {

  const navigate = useNavigate();
  const [searchParams, setSearchParams] = useSearchParams();

  const [directionFrom, setDirectionFrom] = useState('');
  const [directionTo, setDirectionTo] = useState('');

  useEffect(() => {
    const line = searchParams.get(TimetableQueryParams.Line);
    if (line) {
      timetablesApi.getLineDirections(line).then(directions => {
        if (directions) {
          setDirectionFrom(directions.directionFrom);
          setDirectionTo(directions.directionTo);
        }
      });
    }
  }, []);

  const getNextLink = (direction: string) =>
    `/station-list` +
    `?${TimetableQueryParams.Line}=${searchParams.get(TimetableQueryParams.Line)}` +
    `&${TimetableQueryParams.Direction}=${direction}`;

  return (
    <div className={`${styles.pageContainer}`}>
      <Link className={`${styles.a}`} to="/lines">
        <img src="/icons/go-back.svg" alt="go back" />
      </Link>
      <h2 className={`${styles.h2}`}>WSKAŻ KIERUNEK</h2>

      <div className={`${styles.buttonContainer}`}>
        <button
          onClick={() => {
            navigate(getNextLink(directionFrom));
          }}
          className={styles.buttonForth}
        >
          {directionFrom}
        </button>
        <button
          onClick={() => {
            navigate(getNextLink(directionTo));
          }}
          className={styles.buttonBack}
        >
          {directionTo}
        </button>
      </div>
    </div>
  );
};

export default Direction;
