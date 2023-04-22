import styles from './RadioButtonGroup.module.scss';
import { FunctionComponent, ReactElement } from 'react';
import RadioButtonGroupElementProps from './RadioButtonGroupElementProps';

interface RadioButtonGroupProps {
  children: ReactElement<RadioButtonGroupElementProps> | Array<ReactElement<RadioButtonGroupElementProps>>;
  vertical: boolean;
}

const RadioButtonGroup: FunctionComponent<RadioButtonGroupProps> = ({ children, vertical = false }) => {
  return <div className={`${styles.container} ${vertical ? styles.vertical : styles.horizontal}`}>{children}</div>;
};

export default RadioButtonGroup;
