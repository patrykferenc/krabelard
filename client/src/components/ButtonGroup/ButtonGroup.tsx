import styles from './ButtonGroup.module.scss';
import { FunctionComponent } from 'react';

interface ButtonGroupProps {
  buttons: Button[];
  maxPerRow: number;
  color: string;
  fontSize?: string;
  fontWeight?: string;
  borderWidth?: string;
  paddingTop?: string;
  paddingBottom?: string;
}

export interface Button {
  label: string;
  action: () => void;
  color?: string;
}

const ButtonGroup: FunctionComponent<ButtonGroupProps> = ({
  buttons,
  maxPerRow,
  color,
  fontSize,
  fontWeight,
  borderWidth,
  paddingTop,
  paddingBottom,
}) => {
  return (
    <div className={styles.row} style={{ gridTemplateColumns: `repeat(${maxPerRow}, 1fr)` }}>
      {buttons.map((row, index) => (
        <button
          className={`${styles.button} ${styles.en}`}
          key={index}
          onClick={buttons[index].action}
          style={{
            fontSize: fontSize,
            fontWeight: fontWeight,
            borderWidth: borderWidth,
            paddingTop: paddingTop,
            paddingBottom: paddingBottom,
            color: row.color ?? color,
            borderColor: row.color ?? color,
            borderRadius: `
                ${index === 0 ? '21px' : '0'}
                ${
                  index === maxPerRow - 1 || (index === buttons.length - 1 && index < maxPerRow - 1)
                    ? '21px'
                    : '0'
                }
                ${
                  index === buttons.length - 1 ||
                  (index % maxPerRow === maxPerRow - 1 &&
                    index === buttons.length - (buttons.length % maxPerRow) - 1)
                    ? '21px'
                    : '0'
                }
                ${
                  index === buttons.length - (maxPerRow === 1 ? 1 : buttons.length % maxPerRow)
                    ? '21px'
                    : '0'
                }`,
          }}
        >
          {buttons[index].label}
        </button>
      ))}
    </div>
  );
};

export default ButtonGroup;
