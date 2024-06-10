import React from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { CreateGoalCompnent } from './create-new-goal-component/CreateGoalComponent';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<CreateGoalCompnent/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
