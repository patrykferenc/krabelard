import styles from './PrimaryButton.module.scss';
import { FunctionComponent } from 'react';

interface PrimaryButtonProps {
  text: string;
  onClick: () => void;
}

const PrimaryButton: FunctionComponent<PrimaryButtonProps> = ({ text, onClick }) => {
  return (
    <button className={`${styles.button}`} onClick={onClick}>
      {text}
    </button>
  );
};

export default PrimaryButton;
