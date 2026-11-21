package com.example.MovieBookingApplication.Service;

import com.example.MovieBookingApplication.DTO.TheaterDTO;
import com.example.MovieBookingApplication.Entity.Theater;
import com.example.MovieBookingApplication.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    public Theater addTheater(TheaterDTO theaterDTO)
    {
        Theater theater = new Theater();
        theater.setTheaterName(theaterDTO.getTheaterName());
        theater.setTheaterLocation(theaterDTO.getTheaterLocation());
        theater.setTheaterCapacity(theaterDTO.getTheaterCapacity());
        theater.setTheaterScreenType(theaterDTO.getTheaterScreenType());

        return  theaterRepository.save(theater);
    }

    public List<Theater> getTheaterByLocation(String location)
    {
        Optional<List<Theater>> listoftheater = theaterRepository.findByTheaterLocation(location);
        if(listoftheater.isPresent())
        {
            return listoftheater.get();
        }else{
            throw new RuntimeException("No Theater found in the the specified location = "+ location);
        }
    }

    public Theater updateThreater(Long id,TheaterDTO theaterDTO)
    {
        Optional<Theater> theater = theaterRepository.findById(id);
        if(theater.isPresent())
        {
            Theater theater1= theater.get();

            theater1.setTheaterName(theaterDTO.getTheaterName());
            theater1.setTheaterLocation(theaterDTO.getTheaterLocation());
            theater1.setTheaterCapacity(theaterDTO.getTheaterCapacity());
            theater1.setTheaterScreenType(theaterDTO.getTheaterScreenType());

            return theaterRepository.save(theater1);

        }else{
            throw new RuntimeException("No theater found for the theater id ="+id);
        }
    }

    public void  deleteTheater(Long id)
    {
        theaterRepository.deleteById(id);
    }
}
