import React from "react";
import { useNavigate } from "react-router-dom";
import { logoutUser } from "../api/auth";
import "../styles/App.css";

function Navbar() {
  const navigate = useNavigate();

  const handleLogout = () => {
    logoutUser();
    navigate("/login");
  };

  return (
    <nav className="navbar">
      <div className="logo">Task Board</div>
      <button className="logout-btn" onClick={handleLogout}>
        Logout
      </button>
    </nav>
  );
}

export default Navbar;
