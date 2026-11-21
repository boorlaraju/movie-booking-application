package com.example.MovieBookingApplication.Repository;

import com.example.MovieBookingApplication.Entity.Booking;
import com.example.MovieBookingApplication.Entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    Optional<List<Booking>> findByUserId(Long userId);
    Optional<List<Booking>> findByShowId(Long showId);
    Optional<List<Booking>>findByBookingStatus(BookingStatus status);
}
