import React from "react";
import { Clapperboard, Ticket, UserRound, LogOut, ShieldCheck } from 'lucide-react';
import { Link, NavLink, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

export default function Header() {
  const { user, isAuthenticated, isAdmin, logout } = useAuth();
  const navigate = useNavigate();
  const handleLogout = () => { logout(); navigate('/'); };
  return <header className="site-header">
    <div className="container header-inner">
      <Link to="/" className="brand"><span className="brand-icon"><Clapperboard size={21}/></span><span>reel<span className="brand-accent">time</span><small>YOUR NEXT MOVIE NIGHT</small></span></Link>
      <nav className="main-nav">
        <NavLink to="/" end>Discover</NavLink>
        {isAuthenticated && <NavLink to="/bookings"><Ticket size={16}/> My bookings</NavLink>}
        {isAdmin && <NavLink to="/admin"><ShieldCheck size={16}/> Admin</NavLink>}
      </nav>
      <div className="header-actions">
        {isAuthenticated ? <><span className="welcome-user"><UserRound size={16}/> {user?.username}</span><button className="btn btn-outline-light btn-sm" onClick={handleLogout}><LogOut size={15}/> Logout</button></> : <><Link className="login-link" to="/login">Log in</Link><Link className="btn btn-primary btn-sm" to="/register">Get started</Link></>}
      </div>
    </div>
  </header>;
}
