import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from './pages/Login';
import Listar from './pages/Listar';
function App() {
  return (
    <Routes>
     
            <Route path="/" exact element={<Login/>}/> 
            <Route path="/list" exact element={<Listar/>}/> 


    </Routes>
  );
}

export default App;
