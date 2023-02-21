import styles from './CheckboxGroup.module.scss';
import {FunctionComponent, useEffect, useState} from "react";

interface CheckboxProps {
  isText: boolean,
  labels: string[],
  checked: boolean,
  selectionChanged: ((checked: boolean[]) => void);
};

const getId = (l: string, i: number) => `${l}_${i.toString()}`;

const CheckboxGroup: FunctionComponent<CheckboxProps> = ({isText, labels, checked, selectionChanged}) => {
  const [checkedBoxes, setCheckedBoxes] = useState(Array(labels.length).fill(checked));
  const check = (i: number) => {
    checkedBoxes[i] = !checkedBoxes[i];
    setCheckedBoxes(checkedBoxes);
    selectionChanged(checkedBoxes);
  };
  return (
    <div className={`${styles.groupContainer}`}>
      {
        labels.map((l, i) => (
          <div key={getId(l, i)} className={`${styles.checkbox}`}>
            <input defaultChecked={checked} onChange={() => {check(i)}} id={getId(l, i)} type="checkbox" className={`${styles.input}`}/>
            <label htmlFor={`${l}_${i.toString()}`} className={`${styles.label}`}>
              {
                isText ? null : <img src={l} alt=""/>
              }
            </label>
          </div>
        ))
      }
    </div>
  );
}

export default CheckboxGroup;