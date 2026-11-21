package com.example.MovieBookingApplication.Controller;

import com.example.MovieBookingApplication.DTO.MovieDTO;
import com.example.MovieBookingApplication.Entity.Movie;
import com.example.MovieBookingApplication.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/addmovie")
    public ResponseEntity<Movie> addMovie(@RequestBody MovieDTO moviedto)
    {
        return ResponseEntity.ok(movieService.addMovie(moviedto));
    }

    @GetMapping("/getallmovies")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Movie>> getAllMovies()
    {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/getmoviebygenre")
    public ResponseEntity<List<Movie>> getMovieByGenre(@RequestParam String genre)
    {
        return ResponseEntity.ok(movieService.getMovieByGenre(genre));
    }

    @GetMapping("/getmoviebylanguate")
    public ResponseEntity<List<Movie>> getMovieBylanguage(@RequestParam String language)
    {
        return ResponseEntity.ok(movieService.getMovieBylanguage(language));
    }

    @GetMapping("/getmoviebytitle")
    public ResponseEntity<List<Movie>> getMovieByTitle(@RequestParam String title)
    {
        return ResponseEntity.ok(movieService.getMovieByTitle(title));
    }

    @PutMapping("/updatemovie/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody MovieDTO movietdto)
    {
        return ResponseEntity.ok(movieService.updateMovie(id,movietdto));
    }

    @DeleteMapping("/deletemovie/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletemovie(@PathVariable Long id)
    {
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }






}
