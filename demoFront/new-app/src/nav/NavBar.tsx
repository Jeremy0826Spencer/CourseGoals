import React from "react";
import { useNavigate } from "react-router-dom";

export const NavBar: React.FC = () => {
  const navigate = useNavigate()
  const clearLocalAndLogout = () => {
    localStorage.clear();
    navigate('/')
  }
  return (
    <nav className="bg-[#6EACDA] shadow-md py-4">
      <div className="container mx-auto flex justify-between items-center">
        <div className="text-xl font-bold text-[#021526]">
          <a href="/">Course Goals</a>
        </div>
        <div className="flex space-x-4">
          <a href="/profile" className="text-[#021526] hover:underline">
            Profile
          </a>
          <a href="/userGoals" className="text-[#021526] hover:underline">
            Goals
          </a>
          <button
            className="text-[#021526] hover:underline"
            onClick={() => {
              clearLocalAndLogout();
            }}
          >
            Logout
          </button>
        </div>
      </div>
    </nav>
  );
};
