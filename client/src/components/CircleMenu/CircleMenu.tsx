import styles from './CircleMenu.module.scss'
import {Children, FunctionComponent, ReactElement, ReactNode} from 'react';
import {CircleProps} from "./Circle/Circle";

interface CircleMenuProps {
  main: ReactNode
  children: ReactNode,
  startFrom: number
};

const CircleMenu: FunctionComponent<CircleMenuProps> = ({children, main, startFrom}) => {
  if (Children.count(children) > 9) console.error('to many children! max is 9');
  if (!startFrom) startFrom = 0;
  const getIndex = (i: number): string => (((i + startFrom) % 8) + 1).toString();
  return (
    <div className={`${styles.circles}`}>
      <div className={`${styles.mainCircle}`}>{main}</div>
      {
        // @ts-ignore
        Children.map(children, (child, i) => <div className={`${styles.childWrap}`} number={getIndex(i)}>{child}</div>)
      }
    </div>
  );
};

export default CircleMenu;
