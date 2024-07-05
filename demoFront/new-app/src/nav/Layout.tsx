import { useLocation } from "react-router-dom";
import { NavBar } from "./NavBar";
import { ReactNode } from "react";

type LayoutProps = {
  children: ReactNode;
};

export const Layout: React.FC<LayoutProps> = ({ children }) => {
  const location = useLocation();
  const hideNavBar =
    location.pathname === "/" || location.pathname === "/register";

  return (
    <>
      {!hideNavBar && <NavBar />}
      {children}
    </>
  );
};
