#!/bin/sh
if [ -z $1 ] ; then
  echo "specify name of component"
  return
fi

name=$(echo $1 | sed 's|.*/||')

mkdir -p src/components/$1
echo "import styles from './$name.module.scss'
import {FunctionComponent} from 'react';

interface $(echo $name)Props {}

const $name: FunctionComponent<$(echo $name)Props> = () => {
  return (
    <></>
  );
};

export default $name;" > "src/components/$1/$name.tsx"
touch "src/components/$1/$name.module.scss"
