import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from './pages/Login';
function App() {
  return (
    <Routes>
     
            <Route path="/" exact element={<Login/>}/> 


    </Routes>
  );
}

export default App;
