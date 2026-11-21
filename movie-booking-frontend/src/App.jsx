
import React, { useEffect } from 'react';
import { Route, Routes, useLocation, Link } from 'react-router-dom';

import Navbar from './components/Navbar';
import Footer from './components/Footer';
import ProtectedRoute from './components/ProtectedRoute';

import Home from './pages/Home';
import Movies from './pages/Movies';
import MovieDetails from './pages/MovieDetails';
import BookShow from './pages/BookShow';
import Bookings from './pages/Bookings';
import Admin from './pages/Admin';
import { Login, Register } from './pages/AuthPages';

function ScrollToTop() {
  const { pathname } = useLocation();

  useEffect(() => {
    window.scrollTo({
      top: 0,
      behavior: 'smooth',
    });
  }, [pathname]);

  return null;
}

function NotFound() {
  return (
    <main className="container page-space empty-state">
      <span>🎬</span>
      <h1>That scene isn't in this movie.</h1>
      <p>The page you are looking for does not exist.</p>
      <Link to="/" className="btn btn-accent rounded-pill px-4">
        Back to home
      </Link>
    </main>
  );
}

export default function App() {
  const location = useLocation();
  const authPage = ['/login', '/register'].includes(location.pathname);

  return (
    <>
      <ScrollToTop />

      {!authPage && <Navbar />}

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/movies" element={<Movies />} />
        <Route path="/movies/:id" element={<MovieDetails />} />

        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />

        <Route
          path="/book/:showId"
          element={
            <ProtectedRoute>
              <BookShow />
            </ProtectedRoute>
          }
        />

        <Route
          path="/bookings"
          element={
            <ProtectedRoute>
              <Bookings />
            </ProtectedRoute>
          }
        />

        <Route
          path="/admin"
          element={
            <ProtectedRoute admin>
              <Admin />
            </ProtectedRoute>
          }
        />

        <Route path="*" element={<NotFound />} />
      </Routes>

      {!authPage && <Footer />}
    </>
  );
}
