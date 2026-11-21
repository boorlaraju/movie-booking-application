import React from "react";
import { Film, Ticket, UserRound, LogOut, ShieldCheck } from 'lucide-react';
import { Link, NavLink, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

export default function Navbar() {
  const { user, token, logout, isAdmin } = useAuth();
  const navigate = useNavigate();
  const signOut = () => { logout(); navigate('/'); };
  return <header className="site-header"><nav className="navbar navbar-expand-lg container py-3">
    <Link to="/" className="navbar-brand brand"><span className="brand-icon"><Film size={20}/></span>cine<span>verse</span></Link>
    <button className="navbar-toggler custom-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNav" aria-controls="mainNav" aria-expanded="false" aria-label="Toggle navigation"><span>☰</span></button>
    <div className="collapse navbar-collapse" id="mainNav">
      <div className="navbar-nav mx-auto nav-links"><NavLink to="/" className="nav-link">Explore</NavLink><NavLink to="/movies" className="nav-link">Movies</NavLink>{token && <NavLink to="/bookings" className="nav-link">My bookings</NavLink>}{isAdmin && <NavLink to="/admin" className="nav-link"><ShieldCheck size={15} className="me-1"/>Admin</NavLink>}</div>
      <div className="d-flex gap-2 align-items-center mt-3 mt-lg-0">{token ? <><span className="welcome-user"><UserRound size={15}/> {user?.username}</span><button className="btn btn-outline-light btn-sm rounded-pill px-3" onClick={signOut}><LogOut size={15} className="me-1"/> Sign out</button></> : <><Link className="btn btn-link text-light text-decoration-none" to="/login">Log in</Link><Link className="btn btn-accent btn-sm rounded-pill px-4" to="/register">Get started</Link></>}</div>
    </div>
  </nav></header>;
}
