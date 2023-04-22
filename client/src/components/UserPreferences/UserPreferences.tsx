import styles from './UserPreferences.module.scss';
import React, { FunctionComponent } from 'react';
import CircleMenu from '../CircleMenu/CircleMenu';
import Circle from '../CircleMenu/Circle/Circle';
import { Link } from 'react-router-dom';

interface UserPreferencesProps {}

const UserPreferences: FunctionComponent<UserPreferencesProps> = () => {
  const mainElement = (
    <div className={`${styles.main}`}>
      <img src="/icons/user.svg" alt="your profile" />
      <p>TWÓJ PROFIL</p>
    </div>
  );
  return (
    <div className={`${styles.container}`}>
      <Link to="/select">
        <img src="/icons/route-with-border.svg" alt="select" className={styles.userIcon} />
      </Link>
      <CircleMenu main={mainElement} startFrom={7}>
        <Circle disabled={false}>
          <Link className={`${styles.a}`} to="">
            <img src="/icons/logout.svg" alt="logout" />
            <p>WYLOGUJ SIĘ</p>
          </Link>
        </Circle>
        <Circle disabled={false}>
          <Link className={`${styles.a}`} to="/routes">
            <img src="/icons/route2.svg" alt="routes" />
            <p>TRASY</p>
          </Link>
        </Circle>
        <Circle disabled={false}>
          <Link className={`${styles.a}`} to="">
            <img src="/icons/paper.svg" alt="licences" />
            <p>LICENCJE</p>
          </Link>
        </Circle>
        <Circle disabled={false}>
          <Link className={`${styles.a}`} to="">
            <img src="/icons/key.svg" alt="privacy" />
            <p>PRYWATNOŚĆ</p>
          </Link>
        </Circle>
        <Circle disabled={false}>
          <Link className={`${styles.a}`} to="">
            <img src="/icons/delete-profile.svg" alt="delete profile" />
            <p>USUŃ PROFIL</p>
          </Link>
        </Circle>
      </CircleMenu>
    </div>
  );
};

export default UserPreferences;
