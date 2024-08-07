import React from "react";
import { NavLink } from "react-router-dom";

export const Layout: React.FC<React.PropsWithChildren<{}>> = ({ children }) => {
  return (
    <div className="min-h-screen flex flex-col">
      <header className="bg-gray-800 text-white p-4">
        <nav className="flex justify-between container mx-auto">
          <div>
            <NavLink to="/" className="mr-4">
              Home
            </NavLink>
            <NavLink to="/profile" className="mr-4">
              Profile
            </NavLink>
            <NavLink to="/admin" className="mr-4">
              Admin
            </NavLink>
          </div>
          <div>
            <NavLink to="/login" className="mr-4">
              Login
            </NavLink>
            <NavLink to="/register">Register</NavLink>
          </div>
        </nav>
      </header>
      <main className="flex-1 container mx-auto p-4">{children}</main>
      <footer className="bg-gray-800 text-white p-4 text-center">
        &copy; 2024 Your Company
      </footer>
    </div>
  );
};
