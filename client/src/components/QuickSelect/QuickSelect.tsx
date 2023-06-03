import styles from './QuickSelect.module.scss'
import {useContext, useState} from "react";
import {LoggedInContext} from "../../App";
import PrimaryButton from "../PrimaryButton/PrimaryButton";
import CircleMenu from "../CircleMenu/CircleMenu";
import Circle from "../CircleMenu/Circle/Circle";
import {Link} from "react-router-dom";

export default function QuickSelect() {
  const [savedLocations, setSavedLocations] = useState(['Kochanka 1', 'Kochanka 2', 'Kochanka 3']);
  const {loggedIn, setLoggedIn} = useContext(LoggedInContext);
  const isLoggedIn = () => loggedIn && loggedIn !== '';
  const main = <Link className={`${styles.mainCircle}`} to='/find-a-way'>
    <img src="icons/route.svg" alt="route"/>
  </Link>;
  return (
    <div className={`${styles.pageContainer}`}>
      <Link to="/preferences"><img src="icons/user-with-border.svg" alt="user" className={styles.userIcon}/></Link>
      <CircleMenu main={main} startFrom={0}>
        <Circle disabled={false}>
          <div>
            <p className={`${styles.departureLabel}`}>WYJAZD O</p>
            <p className={`${styles.departureHour}`}>14:01</p>
          </div>
        </Circle>
        <Circle disabled={false}>
          <Link to="/lines">
            <img src="icons/timetables.svg" alt="timetables"/>
          </Link>
        </Circle>
        <Circle disabled={!isLoggedIn()}>
          <img src="icons/work.svg" alt="work"/>
        </Circle>
        <Circle disabled={!isLoggedIn()}>
          <img src="icons/home.svg" alt="home"/>
        </Circle>
        <Circle disabled={!isLoggedIn()}>
          <img src="icons/school.svg" alt="school"/>
        </Circle>
        {
          savedLocations.map(sl => (
            <Circle key={sl} disabled={!isLoggedIn()}>
              <div key={sl} className={`${styles.savedLocationContent}`}>
                <img src="icons/saved-place.svg" alt={`saved place - ${sl}`}/>
                <p className={`${styles.savedLocationLabel}`}>{sl}</p>
              </div>
            </Circle>
          ))
        }
      </CircleMenu>
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