import styles from './QuickSelect.module.scss'
import {useContext, useEffect, useState} from "react";
import {LoggedInContext} from "../../App";
import PrimaryButton from "../PrimaryButton/PrimaryButton";

export default function QuickSelect() {
  const [savedLocations, setSavedLocations] = useState(['Kochanka 1', 'Kochanka 2', 'Kochanka 3']);
  const {loggedIn, setLoggedIn} = useContext(LoggedInContext);
  const isLoggedIn = () => loggedIn && loggedIn !== '';
  return (
    <div className={`${styles.pageContainer}`}>
      <img src="icons/user.svg" alt="user" className={styles.userIcon}/>
      <div className={`${styles.circles}`}>
        <a className={`${styles.mainCircle}`} href='find-a-way'>
          <img src="icons/route.svg" alt="route"/>
        </a>
        <div className={`${styles.circle}`}>
          <div>
            <p className={`${styles.departureLabel}`}>WYJAZD O</p>
            <p className={`${styles.departureHour}`}>14:01</p>
          </div>
        </div>
        <div className={`${styles.circle}`}>
          <img src="icons/timetables.svg" alt="timetables"/>
        </div>
        <div className={`${styles.circle} ${isLoggedIn() ? '' : styles.disabled}`}>
          <img src="icons/work.svg" alt="work"/>
        </div>
        <div className={`${styles.circle} ${isLoggedIn() ? '' : styles.disabled}`}>
          <img src="icons/home.svg" alt="home"/>
        </div>
        <div className={`${styles.circle} ${isLoggedIn() ? '' : styles.disabled}`}>
          <img src="icons/school.svg" alt="school"/>
        </div>
        {
          savedLocations.map(sl => (
            <div key={sl} className={`${styles.circle} ${isLoggedIn() ? '' : styles.disabled}`}>
              <div className={`${styles.savedLocationContent}`}>
                <img src="icons/saved-place.svg" alt={`saved place - ${sl}`}/>
                <p className={`${styles.savedLocationLabel}`}>{sl}</p>
              </div>
            </div>
          ))
        }
      </div>
      {
        isLoggedIn() ? (<></>) : (
          <div className={`${styles.buttonContainer}`}>
            <PrimaryButton text='zaloguj się' onClick={() => {}}/>
            {loggedIn}
          </div>
        )
      }
    </div>
  );
}