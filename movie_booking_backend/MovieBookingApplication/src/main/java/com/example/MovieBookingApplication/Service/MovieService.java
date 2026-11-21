package com.example.MovieBookingApplication.Service;

import com.example.MovieBookingApplication.DTO.MovieDTO;
import com.example.MovieBookingApplication.Entity.Movie;
import com.example.MovieBookingApplication.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie addMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setDescription(movieDTO.getDescription());
        movie.setGenre(movieDTO.getGenre());
        movie.setLanguage(movieDTO.getLanguage());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setDuration(movieDTO.getDuration());
        movie.setName(movieDTO.getName());

        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public List<Movie> getMovieByGenre(String genre) {
        Optional<List<Movie>> moviesbygenre = movieRepository.findByGenre(genre);
        if (moviesbygenre.isPresent()) {
            return moviesbygenre.get();
        } else {
            throw new RuntimeException("No movies found for genre = " + genre);
        }
    }

    public List<Movie> getMovieBylanguage(String languate) {
        Optional<List<Movie>> moviesbylanguate = movieRepository.findByLanguage(languate);
        if (moviesbylanguate.isPresent()) {
            return moviesbylanguate.get();
        } else {
            throw new RuntimeException("No movies found for language = " + languate);
        }
    }

    public List<Movie> getMovieByTitle(String title) {

        Optional<List<Movie>> moviesbytitle = movieRepository.findByName(title);
        if (moviesbytitle.isPresent()) {
            return moviesbytitle.get();
        } else {
            throw new RuntimeException("No movies found for Title = " + title);
        }

    }

    public Movie updateMovie(@PathVariable Long id, @RequestBody MovieDTO movietdto)
    {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No movie found for the id ="+id));

        movie.setDescription(movietdto.getDescription());
        movie.setGenre(movietdto.getGenre());
        movie.setLanguage(movietdto.getLanguage());
        movie.setReleaseDate(movietdto.getReleaseDate());
        movie.setDuration(movietdto.getDuration());

        return movieRepository.save(movie);


    }

    public void  deleteMovie( Long id)
    {
        movieRepository.deleteById(id);
    }






}
