import styles from './VerticalSeparator.module.scss'
import {FunctionComponent} from 'react';

interface VerticalSeparatorProps {}

const VerticalSeparator: FunctionComponent<VerticalSeparatorProps> = () => {
  return <div className={`${styles.separator}`}><div></div></div>;
};

export default VerticalSeparator;