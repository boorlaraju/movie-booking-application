import React from 'react';
import { useEffect, useState } from 'react';
import { Search } from 'lucide-react';
import api, { getErrorMessage } from '../api/client';
import MovieCard from '../components/MovieCard';
import Loader from '../components/Loader';
import Notice from '../components/Notice';
export default function Movies() {
 const [movies,setMovies]=useState([]); const [query,setQuery]=useState(''); const [genre,setGenre]=useState('All'); const [loading,setLoading]=useState(true); const [error,setError]=useState('');
 useEffect(()=>{api.get('/api/movies/getallmovies').then(r=>setMovies(Array.isArray(r.data)?r.data:[])).catch(e=>setError(getErrorMessage(e))).finally(()=>setLoading(false));},[]);
 const genres=['All',...new Set(movies.map(m=>m.genre).filter(Boolean))]; const filtered=movies.filter(m=>(`${m.name} ${m.genre} ${m.language}`.toLowerCase().includes(query.toLowerCase()))&&(genre==='All'||m.genre===genre));
 return <main className="container page-space"><div className="page-intro"><div className="eyebrow">THE MOVIE COLLECTION</div><h1>Choose your <span>next escape.</span></h1><p>From edge-of-your-seat thrillers to stories that warm your heart.</p></div><div className="movie-controls"><div className="search-bar"><Search size={18}/><input value={query} onChange={e=>setQuery(e.target.value)} placeholder="Search titles, genres, languages..."/></div><div className="genre-pills">{genres.map(g=><button key={g} className={genre===g?'active':''} onClick={()=>setGenre(g)}>{g}</button>)}</div></div>{error&&<Notice>{error}</Notice>}{loading?<Loader label="Loading movie collection..."/>:filtered.length?<div className="movie-grid">{filtered.map((m,i)=><MovieCard key={m.id??i} movie={m} index={i}/>)}</div>:<div className="empty-state"><span>🎞️</span><h3>No movies to show</h3><p>Try another filter or check that your backend has movie records.</p></div>}</main>;
}
