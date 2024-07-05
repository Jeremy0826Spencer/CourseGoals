import React from "react";
import "./App.css";
import { BrowserRouter, Route, Routes, useLocation } from "react-router-dom";
import { LoginComponent } from "./login-component/LoginComponent";
import { RegisterComponent } from "./register-component/RegisterComponent";
import { CreateGoalCompnent } from "./create-new-goal-component/CreateGoalComponent";
import { NavBar } from "./nav/NavBar";
import { Layout } from "./nav/Layout";
import { UserProfile } from "./user-profile/UserProfile";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Layout>
          <Routes>
            <Route path="/" element={<LoginComponent />} />
            <Route path="/register" element={<RegisterComponent />} />
            <Route path="/userGoals" element={<CreateGoalCompnent />} />
            <Route path="/profile" element={<UserProfile />} />
          </Routes>
        </Layout>
      </BrowserRouter>
    </div>
  );
}

export default App;
