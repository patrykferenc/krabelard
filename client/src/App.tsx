import './App.module.css';
import { createBrowserRouter, createRoutesFromElements, Navigate, Route, RouterProvider } from 'react-router-dom';
import QuickSelect from './components/QuickSelect/QuickSelect';
import { createContext, useState } from 'react';
import FindAWay from './components/FindAWay/FindAWay';
import UserPreferences from './components/UserPreferences/UserPreferences';
import RoutePreferences from './components/RoutePreferences/RoutePreferences';

const initialLoggedIn = { loggedIn: null, setLoggedIn: (username: string) => {} };
export const LoggedInContext = createContext(initialLoggedIn);

export default function App() {
  const [loggedIn, setLoggedIn] = useState(initialLoggedIn.loggedIn);
  const router = createBrowserRouter(
    createRoutesFromElements([
      <Route path="/find-a-way" element={<FindAWay />} />,
      <Route path="/select" element={<QuickSelect />} />,
      <Route path="/preferences" element={<UserPreferences />} />,
      <Route path="routes" element={<RoutePreferences />} />,
      <Route path="/" element={<Navigate to="/select" />} />
    ])
  );
  return (
    // @ts-ignore
    <LoggedInContext.Provider value={{ loggedIn, setLoggedIn }}>
      <RouterProvider router={router}></RouterProvider>
    </LoggedInContext.Provider>
  );
}
