import React from "react";
import { useEffect, useState } from 'react';
import { ArrowRight, Play, Search, Sparkles, Ticket } from 'lucide-react';
import { Link } from 'react-router-dom';
import api, { getErrorMessage } from '../api/client';
import MovieCard from '../components/MovieCard';
import Loader from '../components/Loader';
import Notice from '../components/Notice';

export default function Home() {
  const [movies, setMovies] = useState([]); const [loading, setLoading] = useState(true); const [error, setError] = useState(''); const [query, setQuery] = useState('');
  useEffect(() => { api.get('/api/movies/getallmovies').then(r => setMovies(Array.isArray(r.data) ? r.data : [])).catch(e => setError(getErrorMessage(e))).finally(() => setLoading(false)); }, []);
  const filtered = movies.filter(m => `${m.name} ${m.genre} ${m.language}`.toLowerCase().includes(query.toLowerCase()));
  return <>
    <section className="hero-section"><div className="hero-orb orb-one"/><div className="hero-orb orb-two"/><div className="container hero-content"><div className="hero-copy"><div className="eyebrow"><Sparkles size={14}/> YOUR NEXT GREAT NIGHT OUT</div><h1>Stories hit<br/>different on the <span>big screen.</span></h1><p>Find your movie. Pick your seats. Make it a night to remember.</p><div className="hero-actions"><a href="#movies" className="btn btn-accent btn-lg rounded-pill px-4">Explore movies <ArrowRight size={17} className="ms-2"/></a><Link to="/movies" className="btn btn-ghost btn-lg rounded-pill px-4"><Play size={16} className="me-2"/> View all films</Link></div><div className="hero-trust"><div className="avatar-stack"><i>R</i><i>K</i><i>A</i><i>+</i></div><span><strong>Movie lovers</strong><br/>make every seat count</span></div></div><div className="hero-art" aria-hidden="true"><div className="hero-ticket"><div className="ticket-top"><span>CINEVERSE</span><Ticket size={19}/></div><div className="ticket-screen"><div className="screen-moon"/><div className="screen-mountain mountain-a"/><div className="screen-mountain mountain-b"/><div className="screen-line"/></div><div className="ticket-bottom"><div><small>YOUR NEXT ADVENTURE</small><strong>ONE NIGHT. ONE STORY.</strong></div><div className="ticket-code">CV<br/>026</div></div></div><div className="floating-chip chip-top">✦ &nbsp; Premium experience</div><div className="floating-chip chip-bottom"><span className="live-dot"/> Your seat is waiting</div></div></div><div className="hero-bottom container"><span>THE MAGIC STARTS HERE</span><div className="hero-line"/><span>SCROLL TO DISCOVER ↓</span></div></section>
    <section id="movies" className="section-space container"><div className="section-heading"><div><div className="eyebrow">NOW SHOWING</div><h2>Find your <span>next favourite.</span></h2><p>Big stories. Bigger screens. Your perfect night starts here.</p></div><Link to="/movies" className="text-link">See all movies <ArrowRight size={16}/></Link></div>
      <div className="search-bar mb-4"><Search size={18}/><input value={query} onChange={e => setQuery(e.target.value)} placeholder="Search movies, genres, languages..." aria-label="Search movies"/>{query && <button onClick={() => setQuery('')}>Clear</button>}</div>
      {error && <Notice>{error} Check that Spring Boot is running at http://localhost:8080.</Notice>}
      {loading ? <Loader label="Finding movies for you..."/> : filtered.length ? <div className="movie-grid">{filtered.slice(0, 8).map((m, i) => <MovieCard key={m.id ?? i} movie={m} index={i}/>)}</div> : <div className="empty-state"><span>🎬</span><h3>{movies.length ? 'No matches found' : 'The screen is waiting'}</h3><p>{movies.length ? 'Try a different title, genre, or language.' : 'Add movies using your backend or admin tools, then they will appear here.'}</p></div>}
    </section>
    <section className="experience-band"><div className="container experience-inner"><div><div className="eyebrow">THE CINEVERSE DIFFERENCE</div><h2>Your movie night,<br/><span>made effortless.</span></h2></div><div className="experience-features"><div><span>01</span><strong>Easy booking</strong><p>Your next movie night is just a few clicks away.</p></div><div><span>02</span><strong>Pick your seats</strong><p>Choose the seats that make the moment yours.</p></div><div><span>03</span><strong>All in one place</strong><p>Find your booking details whenever you need them.</p></div></div></div></section>
  </>;
}
