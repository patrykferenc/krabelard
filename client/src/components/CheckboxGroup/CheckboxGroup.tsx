import styles from './CheckboxGroup.module.scss';
import { FunctionComponent, useState, MouseEvent } from 'react';

interface CheckboxProps {
  isText: boolean;
  labels: string[];
  checked: boolean;
  selectionChanged: (checked: boolean[]) => void;
  requireOneSelected: boolean;
}

const getId = (l: string, i: number) => `${l}_${i.toString()}`;

const CheckboxGroup: FunctionComponent<CheckboxProps> = ({ isText, labels, checked, selectionChanged, requireOneSelected }) => {
  const [checkedBoxes, setCheckedBoxes] = useState(() => {
    const array = Array(labels.length).fill(checked);
    if (!checked && requireOneSelected) {
      array[0] = true;
    }
    return array;
  });
  const canToggle = (i: number): boolean =>
    !requireOneSelected || (checkedBoxes[i] && checkedBoxes.filter(cb => cb).length > 1) || !checkedBoxes[i];
  const check = (e: MouseEvent<HTMLInputElement>, i: number) => {
    if (canToggle(i)) {
      checkedBoxes[i] = !checkedBoxes[i];
      setCheckedBoxes(checkedBoxes);
      selectionChanged(checkedBoxes);
    } else {
      e.stopPropagation();
      e.preventDefault();
    }
  };
  return (
    <div className={`${styles.groupContainer}`}>
      {labels.map((l, i) => (
        <div key={getId(l, i)} className={`${styles.checkbox}`}>
          <input
            defaultChecked={checked || (requireOneSelected && i == 0)}
            onClick={e => {
              check(e, i);
            }}
            id={getId(l, i)}
            type="checkbox"
            className={`${styles.input}`}
          />
          <label htmlFor={`${l}_${i.toString()}`} className={`${styles.label}`}>
            {isText ? null : <img src={l} alt="" />}
          </label>
        </div>
      ))}
    </div>
  );
};

export default CheckboxGroup;
