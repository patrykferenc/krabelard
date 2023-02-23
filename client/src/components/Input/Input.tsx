import styles from './Input.module.scss';
import { FunctionComponent } from "react";

interface InputProps {
    label: string,
    placeholder: string,
    labelWidth: string,
    onChange: ((value: string) => void)
};

const Input: FunctionComponent<InputProps> = ({label, placeholder, labelWidth, onChange}) => {
  const inputId = Math.random().toString();
  if (!labelWidth) {
    labelWidth = 'fit-content';
  }
  const change = (e: any) => {
    if (onChange) {
      onChange(e.target.value);
    }
  }
  return (
    <div className={`${styles.container}`}>
      <label htmlFor={inputId} className={`${styles.label}`} style={{width: labelWidth}}>{label}</label>
      <input onChange={change} type="text" id={inputId} placeholder={placeholder} className={`${styles.input}`}/>
    </div>
  );
}

export default Input;