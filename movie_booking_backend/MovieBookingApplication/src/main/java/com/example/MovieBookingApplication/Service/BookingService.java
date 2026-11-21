package com.example.MovieBookingApplication.Service;

import com.example.MovieBookingApplication.DTO.BookingDTO;
import com.example.MovieBookingApplication.Entity.Booking;
import com.example.MovieBookingApplication.Entity.BookingStatus;
import com.example.MovieBookingApplication.Entity.Show;
import com.example.MovieBookingApplication.Entity.User;
import com.example.MovieBookingApplication.Repository.BookingRepository;
import com.example.MovieBookingApplication.Repository.ShowRepository;
import com.example.MovieBookingApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private UserRepository userRepository;

    public Booking createBooking(BookingDTO bookingDTO)
    {
        Show show = showRepository.findById(bookingDTO.getShowId())
                .orElseThrow(()-> new RuntimeException("No show avaialble for the show id = "+ bookingDTO.getShowId()));


        if(!isSeatsAvaiable(show.getId(),bookingDTO.getNoOfSeats())){
            throw new RuntimeException("Seats not available for the show show id = "+show.getId());
        }

        if(bookingDTO.getSeatNumbers().size() != bookingDTO.getNoOfSeats())
        {
            throw new RuntimeException("No of seats and seat numbers must be equal");
        }
        validateDuplicateSeatsNumbers(show.getId(), bookingDTO.getSeatNumbers());

        User user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(()-> new RuntimeException("user not found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setNoOfSeats(bookingDTO.getNoOfSeats());
        booking.setSeatNumbers(bookingDTO.getSeatNumbers());
        booking.setBookingTime(LocalDateTime.now());
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setPrice(calculateTotalPrice(show.getPrice(),bookingDTO.getNoOfSeats()));


        return bookingRepository.save(booking);
    }

    public List<Booking> getUserBookings(Long id)
    {
        Optional<List<Booking>> userbookings= bookingRepository.findByUserId(id);
        if(userbookings.isPresent())
        {
            return userbookings.get();
        }else{
            throw new RuntimeException("No bookings found for the user id = "+id);
        }
    }

    public List<Booking> getShowBookings(Long id)
    {
        Optional<List<Booking>> showbookings= bookingRepository.findByShowId(id);
        if(showbookings.isPresent())
        {
            return showbookings.get();
        }else{
            throw new RuntimeException("No bookings found for the user id = "+id);
        }
    }

    public Booking confirmBooking(Long bookingid)
    {
        Booking booking = bookingRepository.findById(bookingid)
                                    .orElseThrow(()-> new RuntimeException("Booking not found"));

        if(booking.getBookingStatus()!= BookingStatus.PENDING)
        {
            throw new RuntimeException("Booking status is not in pending state");
        }

        // PAYMENT API PROCESS
        booking.setBookingStatus(BookingStatus.CONFIRMED);

        return bookingRepository.save(booking);
    }

    public Booking cancelBooking(Long bookingId)
    {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(()-> new RuntimeException("Boking not found"));

        validateCancelBookings(booking);

        booking.setBookingStatus(BookingStatus.CANCELED);
        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByStatus(BookingStatus status)
    {
        Optional<List<Booking>> bookingsByStatus = bookingRepository.findByBookingStatus(status);

        if(!bookingsByStatus.isPresent()){
            return bookingsByStatus.get();
        }else {
            throw new RuntimeException("No bookings were found the status ="+ status);
        }
    }

    public void validateCancelBookings(Booking booking)
    {
        LocalDateTime showTime = booking.getBookingTime();
        LocalDateTime deadLine = showTime.minusHours(2);
        if(LocalDateTime.now().isAfter(deadLine)){
            throw new RuntimeException("Cannot cancel the booking");
        }

        if(booking.getBookingStatus()==BookingStatus.CANCELED)
        {
            throw new RuntimeException("Booking already been cancelled");
        }
    }

    public boolean isSeatsAvaiable(Long showId, Integer noOfSeats)
    {
        Show show = showRepository.findById(showId)
                .orElseThrow(()-> new RuntimeException(" No show found"));

        int bookedseats = show.getBookings().stream()
                .filter(booking -> booking.getBookingStatus() != BookingStatus.CANCELED)
                .mapToInt(Booking::getNoOfSeats)
                .sum();

        return (show.getTheater().getTheaterCapacity() - bookedseats) >= noOfSeats;

    }

    public void validateDuplicateSeatsNumbers(Long showId , List<String> seatNumbers)
    {

        Show show = showRepository.findById(showId)
                .orElseThrow(()-> new RuntimeException("No show avaialble for the show id = " + showId));

        Set<String> occupiedSeats = show.getBookings().stream()
                                        .filter(b-> b.getBookingStatus()!= BookingStatus.CANCELED)
                                        .flatMap(b -> b.getSeatNumbers().stream())
                                        .collect(Collectors.toSet());

        List<String> duplicateSeats = seatNumbers.stream()
                                        .filter(occupiedSeats::contains)
                                        .toList();


        if(!duplicateSeats.isEmpty())
        {
            throw new RuntimeException("seats already booked");
        }
    }

    public  Double calculateTotalPrice(Double price , Integer noOfSeats)
    {
        return price*noOfSeats;
    }

}
