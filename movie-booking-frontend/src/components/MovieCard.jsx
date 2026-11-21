import React from "react";
import { Clock3, Languages } from 'lucide-react';
import { Link } from 'react-router-dom';

const palettes = ['poster-violet','poster-blue','poster-red','poster-green','poster-amber','poster-pink'];
export default function MovieCard({ movie, index = 0 }) {
  const id = movie.id;
  const initials = (movie.name || 'Movie').split(' ').slice(0, 2).map(x => x[0]).join('').toUpperCase();
  return <article className="movie-card">
    <Link to={`/movies/${id}`} className={`poster ${palettes[index % palettes.length]}`} aria-label={`View ${movie.name}`}>
      <div className="poster-glow"/><span className="poster-mark">{initials}</span><span className="poster-tag">{movie.genre || 'NOW SHOWING'}</span>
    </Link>
    <div className="movie-card-body"><div className="d-flex justify-content-between align-items-start gap-2"><h3>{movie.name || 'Untitled movie'}</h3><span className="movie-rating">★ 8.{(index * 3 + 2) % 10}</span></div>
      <div className="movie-meta"><span><Clock3 size={13}/>{movie.duration ? `${movie.duration} min` : 'Feature film'}</span><span><Languages size={13}/>{movie.language || 'Multiple'}</span></div>
      <p className="movie-description">{movie.description || 'Discover the story on the big screen.'}</p>
      <Link to={`/movies/${id}`} className="movie-book-link">Book tickets <span>↗</span></Link>
    </div>
  </article>;
}
