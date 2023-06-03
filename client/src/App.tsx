import './App.module.css';
import {createBrowserRouter, createRoutesFromElements, Navigate, Route, RouterProvider} from "react-router-dom";
import QuickSelect from "./components/QuickSelect/QuickSelect";
import {createContext, useState} from "react";
import FindAWay from "./components/FindAWay/FindAWay";
import UserPreferences from "./components/UserPreferences/UserPreferences";
import RoutePreferences from "./components/RoutePreferences/RoutePreferences";
import SelectLineForTimetable from './components/SelectLineForTimetable/SelectLineForTimetable';
import StationsList from './components/StationsList/StationsList';
import Direction from './components/Direction/Direction';
import Timetables from './components/Timetables/Timetables';

const initialLoggedIn = { loggedIn: null, setLoggedIn: ((username: string) => {}) };
export const LoggedInContext = createContext(initialLoggedIn);

export default function App() {
  const [loggedIn, setLoggedIn] = useState(initialLoggedIn.loggedIn);
  const router = createBrowserRouter(
    createRoutesFromElements([
      <Route path='/find-a-way' element={<FindAWay/>}/>,
      <Route path='/select' element={<QuickSelect/>}/>,
      <Route path='/preferences' element={<UserPreferences/>}/>,
      <Route path='/routes' element={<RoutePreferences/>}/>,
      <Route path='/lines' element={<SelectLineForTimetable/>}/>,
      <Route path='/direction' element={<Direction/>}/>,
      <Route path='/station-list' element={<StationsList/>}/>,
      <Route path='/timetables' element={<Timetables/>}/>,
      <Route path='/' element={<Navigate to="/select"/>}/>
    ])
  );
  return (
    // @ts-ignore
    <LoggedInContext.Provider value={{ loggedIn, setLoggedIn }}>
      <RouterProvider router={router}></RouterProvider>
    </LoggedInContext.Provider>
  );
}