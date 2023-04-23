import styles from './Input.module.scss';
import { FunctionComponent } from 'react';

interface InputProps {
  label: string;
  placeholder: string;
  labelWidth: string;
  onChange: (value: string) => void;
  labelImg: boolean;
}

const Input: FunctionComponent<InputProps> = ({
  label,
  placeholder,
  labelWidth,
  onChange,
  labelImg,
}) => {
  const inputId = Math.random().toString();
  if (!labelImg) {
    labelImg = false;
  }
  if (!labelWidth) {
    labelWidth = 'fit-content';
  }
  const change = (e: any) => {
    if (onChange) {
      onChange(e.target.value);
    }
  };
  const getLabel = () => (labelImg ? <img src={label} /> : label);
  return (
    <div className={`${styles.container}`}>
      <label
        htmlFor={inputId}
        className={`${styles.label} ${labelImg ? styles.image : styles.noImage}`}
        style={{ width: labelWidth }}
      >
        {getLabel()}
      </label>
      <input
        onChange={change}
        type='text'
        id={inputId}
        placeholder={placeholder}
        className={`${styles.input}`}
      />
    </div>
  );
};

export default Input;
