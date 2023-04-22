import styles from './RoutePreferences.module.scss';
import { FunctionComponent, useState } from 'react';
import { Link } from 'react-router-dom';
import SecondaryButton from '../SecondaryButton/SecondaryButton';
import Input from '../Input/Input';
import PrimaryButton from '../PrimaryButton/PrimaryButton';
import PlaceTypeEnum from '../../enums/place-type.enum';
import RadioButtonGroup from '../RadioButtonGroup/RadioButtonGroup';
import { set } from 'ol/transform';

interface RoutePreferencesProps {}

const RoutePreferences: FunctionComponent<RoutePreferencesProps> = () => {
  // TODO fetch from KrApi
  const placesList = [
    { placeType: PlaceTypeEnum.Home, img: '/icons/home.svg', placeholder: 'ADRES DOMU' },
    { placeType: PlaceTypeEnum.School, img: '/icons/school.svg', placeholder: 'ADRES SZKOŁY' },
    { placeType: PlaceTypeEnum.Work, img: '/icons/work.svg', placeholder: 'ADRES PRACY' }
  ];
  const [places, setPlaces] = useState(placesList);
  const addPlace = () => {
    places.push({ placeType: PlaceTypeEnum.Other, img: 'icons/saved-place.svg', placeholder: 'ADRES MIEJSCA' });
    setPlaces([...places]);
  };
  const removePlace = () => {
    if (places[places.length - 1].placeType == PlaceTypeEnum.Other) {
      places.pop();
      setPlaces([...places]);
    }
  };
  return (
    <div className={`${styles.pageContainer}`}>
      <Link className={`${styles.a}`} to="/preferences">
        <img src="/icons/go-back.svg" alt="go back" />
      </Link>
      <h2 className={`${styles.h2}`}>JAK JEDZIEMY?</h2>
      {/* TODO convert to checkbox */}
      <RadioButtonGroup vertical={true}>
        <SecondaryButton color="#0496FF" text="SZYBKO" onClick={() => {}} />
        <SecondaryButton color="#0496FF" text="WYGODNIE" onClick={() => {}} />
      </RadioButtonGroup>
      <div className={`${styles.separator}`}>
        <div></div>
      </div>
      <h2 className={`${styles.h2}`}>TWOJE CELE</h2>
      {places.map((p, i) => (
        // @ts-ignore
        <Input key={i} label={p.img} placeholder={p.placeholder} labelImg={true} />
      ))}
      <div className={`${styles.buttons}`}>
        <SecondaryButton
          color="#0496FF"
          text="+"
          onClick={() => {
            addPlace();
          }}
        />
        <SecondaryButton color="#E85F5C" text="-" onClick={removePlace} />
      </div>
      <PrimaryButton text="ZAPISZ" onClick={() => {}} />
    </div>
  );
};

export default RoutePreferences;
