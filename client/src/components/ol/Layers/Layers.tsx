import { FunctionComponent, ReactNode } from 'react';

interface LayersProps {
  children: ReactNode;
}

const Layers: FunctionComponent<LayersProps> = ({ children }) => {
  return <div>{children}</div>;
};

export default Layers;
