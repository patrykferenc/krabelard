import styles from './Direction.module.scss';
import { FunctionComponent, useState } from 'react';
import { Link, useNavigate, useSearchParams } from 'react-router-dom';
import TimetableQueryParams from '../../enums/TimetableQueryParams';



interface DirectionListProps {}

const Direction: FunctionComponent<DirectionListProps> = () => {
  const [forth, setForth] = useState('MŁOCINY');
  const [back, setBack] = useState('KABATY');

  const navigate = useNavigate();
  const [searchParams, setSearchParams] = useSearchParams();

  const getNextLink = (direction: string) =>
    `/station-list` +
    `?${TimetableQueryParams.Line}=${searchParams.get(TimetableQueryParams.Line)}` +
    `&${TimetableQueryParams.Direction}=${direction}`;

  const getMapLink = () =>
    `/positions-map` 
    +
    `?${TimetableQueryParams.Line}=${searchParams.get(TimetableQueryParams.Line)}`;


  return (
    <div className={`${styles.pageContainer}`}>
      <Link className={`${styles.a}`} to='/lines'>
        <img src='/icons/go-back.svg' alt='go back' />
      </Link>

      <h2 className={`${styles.h2}`}>WSKAŻ KIERUNEK</h2>
      <div className={`${styles.directionContainer}`}>
        <div className={`${styles.buttonContainer}`}>
          <button
            onClick={() => {
              navigate(getNextLink(forth));
            }}
            className={styles.buttonForth}
          >
            {forth}
          </button>
          <button
            onClick={() => {
              navigate(getNextLink(back));
            }}
            className={styles.buttonBack}
          >
            {back}
          </button>
        </div>
        <button
          onClick={() => {
            navigate(getMapLink());
          }}
          className={`${styles.buttonBottom} `}
        >
          <img className={styles.iconBigger} src='/icons/map-icon.svg' alt='map icon' />
        </button>
      </div>
    </div>
  );
};

export default Direction;
