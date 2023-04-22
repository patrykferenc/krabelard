import styles from './Circle.module.scss';
import { FunctionComponent, ReactNode } from 'react';

export interface CircleProps {
  children: ReactNode;
  disabled: boolean;
}

const Circle: FunctionComponent<CircleProps> = ({ children, disabled }) => {
  return <div className={`${styles.circle} ${disabled && styles.disabled}`}>{children}</div>;
};

export default Circle;
