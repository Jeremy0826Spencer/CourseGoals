import React from 'react';
import { Route, Navigate } from 'react-router-dom';

interface ProtectedRouteProps {
  element: React.ReactElement;
  allowedRoles: string[];
}

export const ProtectedRoute: React.FC<ProtectedRouteProps> = ({ element, allowedRoles }) => {
  const role = localStorage.getItem('role');

  if (role && allowedRoles.includes(role)) {
    return element;
  }

  return <Navigate to="/" replace />;
};
