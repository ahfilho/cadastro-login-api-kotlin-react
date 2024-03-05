import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from './pages/Login';
import Listar from './pages/Listar';
import Hola from './pages/Hola';
function App() {
  return (
    <Routes>

      <Route path="/" exact element={<Login />} />
      <Route path="/list" exact element={<Listar />} />
      <Route path="/hola" exact component={<Hola />} />


    </Routes>
  );
}

export default App;
