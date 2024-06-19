import React from 'react';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { LoginComponent } from './login-component/LoginComponent';
import { RegisterComponent } from './register-component/RegisterComponent';


function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<LoginComponent/>}/>
          <Route path='/register' element={<RegisterComponent/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
