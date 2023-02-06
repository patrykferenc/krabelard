import styles from './PrimaryButton.module.css';

export default function PrimaryButton({text, onClick}) {
  return <button className={`${styles.button}`} onClick={onClick}>{text}</button>
}