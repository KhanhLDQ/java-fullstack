import React, { useEffect } from "react";
import { Outlet, Navigate, useLocation } from "react-router-dom";
import { useAuth } from "../store/auth";

export default function ProtectedRoute() {
  const { isAuthenticated } = useAuth();
  const location = useLocation(); //understand what's the path that end user is trying to access

  useEffect(() => {
    if (!isAuthenticated && location.pathname !== "/login") {
      sessionStorage.setItem("redirectPath", location.pathname); //once end user closes browser or tab -> information will be cleared
    }
  }, [isAuthenticated, location.pathname]);

  //Outlet: dynamic placeholder that represents whatever child component should be rendered for the current route
  return isAuthenticated ? <Outlet /> : <Navigate to="/login" />;
}
