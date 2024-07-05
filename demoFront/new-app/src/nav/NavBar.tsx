import React from "react";

export const NavBar: React.FC = () => {
  return (
    <nav className="bg-gray-100 shadow-md py-4">
      <div className="container mx-auto flex justify-between items-center">
        <div className="text-xl font-bold text-gray-700">
          <a href="/">Course Goals</a>
        </div>
        <div className="flex space-x-4">
          <a href="/profile" className="text-blue-600 hover:underline">
            Profile
          </a>
          <a href="/userGoals" className="text-blue-600 hover:underline">
            Goals
          </a>
        </div>
      </div>
    </nav>
  );
};