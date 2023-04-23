import styles from './SecondaryButton.module.scss';
import { FunctionComponent } from 'react';

interface PrimaryButtonProps {
  text: string;
  onClick: () => void;
  color: string;
}

const SecondaryButton: FunctionComponent<PrimaryButtonProps> = ({ text, onClick, color }) => {
  if (!color) color = '#000000';
  return (
    <button
      style={{ borderColor: color, color: color }}
      className={`${styles.button}`}
      onClick={onClick}
    >
      {text}
    </button>
  );
};

export default SecondaryButton;
