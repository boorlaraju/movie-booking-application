import React from "react";
import { Film, Instagram, Facebook, ArrowUpRight } from 'lucide-react';
import { Link } from 'react-router-dom';
export default function Footer(){
    return <footer className="site-footer">
        <div className="container footer-main">
            <div className="footer-brand">
                <Link to="/" className="brand">
                <span className="brand-icon">
                    <Film size={20}/>
                    </span>cine<span>verse</span>
                    </Link>
                    <p>Good stories. Great company.<br/>A little movie magic, whenever you need it.</p>
                    </div>
                    <div className="footer-links">
                        <div><strong>Explore</strong>
                        <Link to="/movies">All movies</Link>
                        <Link to="/">Home</Link>
                        </div>
                        <div>
                            <strong>Your account</strong>
                            <Link to="/login">Log in</Link>
                            <Link to="/register">Create account</Link>
                            <Link to="/bookings">My bookings</Link>
                            </div><div><strong>Stay in the loop</strong>
                            <span className="footer-social">
                                <Instagram size={17}/><Facebook size={17}/>
                                </span>
                                <span className="footer-small">Made for movie people <ArrowUpRight size={13}/></span>
                                </div>
                                </div>
                                </div>
                                <div className="container footer-bottom">
                                    <span>© {new Date().getFullYear()} CineVerse. All rights reserved.</span>
                                    <span>THE SHOW MUST GO ON.</span></div>
                                </footer>;
                                }
