package com.example.MovieBookingApplication.Service;

import com.example.MovieBookingApplication.DTO.ShowDTO;
import com.example.MovieBookingApplication.Entity.Booking;
import com.example.MovieBookingApplication.Entity.Movie;
import com.example.MovieBookingApplication.Entity.Show;
import com.example.MovieBookingApplication.Entity.Theater;
import com.example.MovieBookingApplication.Repository.MovieRepository;
import com.example.MovieBookingApplication.Repository.ShowRepository;
import com.example.MovieBookingApplication.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;


    public Show createShow(ShowDTO showDTO)
    {
        Movie movie = movieRepository.findById(showDTO.getMovieId())
                .orElseThrow(()-> new RuntimeException("No movie found with movie_id ="+showDTO.getMovieId()));

        Theater theater = theaterRepository.findById(showDTO.getTheaterId())
                .orElseThrow(()-> new RuntimeException("No movie found with theater id ="+showDTO.getTheaterId()));
        Show show = new Show();
        show.setMovie(movie);
        show.setTheater(theater);
        show.setPrice(showDTO.getPrice());
        show.setShowtime(showDTO.getShowtime());

        return showRepository.save(show);
    }

    public List<Show> getAllShows()
    {
        return showRepository.findAll();
    }

    public List<Show> getShowsbyMovie(Long  movieId)
    {
        Optional<List<Show>> movieslist =  showRepository.findByMovieId(movieId);
        if(movieslist.isPresent())
        {
            return movieslist.get();
        }else{
            throw new RuntimeException("No show found for the movie id = "+ movieId);
        }
    }

    public List<Show> getShowsByTheater(Long  theaterId)
    {
        Optional<List<Show>> theaterlist =  showRepository.findByTheaterId(theaterId);
        if(theaterlist.isPresent())
        {
            return theaterlist.get();
        }else{
            throw new RuntimeException("No show found for the theater id = "+ theaterId);
        }
    }

    public Show updateShow(Long id , ShowDTO showDTO)
    {
        Show show = showRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No show found for the id = "+ id));

        Movie movie = movieRepository.findById(showDTO.getMovieId())
                .orElseThrow(()-> new RuntimeException("No movie found with movie_id ="+showDTO.getMovieId()));

        Theater theater = theaterRepository.findById(showDTO.getTheaterId())
                .orElseThrow(()-> new RuntimeException("No movie found with theater id ="+showDTO.getTheaterId()));

        show.setShowtime(showDTO.getShowtime());
        show.setPrice(showDTO.getPrice());
        show.setMovie(movie);
        show.setTheater(theater);

        return showRepository.save(show);
    }

    public void deleteShow(Long showId)
    {
        if(!showRepository.existsById(showId))
        {
            throw new RuntimeException("show show available with the id ="+showId);
        }


        List<Booking> bookings = showRepository.findById(showId).get().getBookings();
        if(bookings.isEmpty())
        {
            throw new RuntimeException("can't delete show with bookings");
        }
        showRepository.deleteById(showId);
    }




}
