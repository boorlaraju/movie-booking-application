package com.example.MovieBookingApplication.Controller;

import com.example.MovieBookingApplication.DTO.BookingDTO;
import com.example.MovieBookingApplication.Entity.Booking;
import com.example.MovieBookingApplication.Entity.BookingStatus;
import com.example.MovieBookingApplication.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/createbokking")
    ResponseEntity<Booking> createBooking (@RequestBody BookingDTO bookingDTO)
    {
        return ResponseEntity.ok(bookingService.createBooking(bookingDTO));
    }

    @GetMapping("/getuserbookings/{id}")
    ResponseEntity<List<Booking>> getUserBookings(@PathVariable Long id)
    {
        return ResponseEntity.ok(bookingService.getUserBookings(id));
    }

    @GetMapping("/getshowbookings/{id}")
    ResponseEntity<List<Booking>> getShowBookings(@PathVariable Long id)
    {
        return ResponseEntity.ok(bookingService.getShowBookings(id));
    }

    @PutMapping("{id}/confirm")
    ResponseEntity<Booking> confirmBooking(@PathVariable Long id)
    {
        return ResponseEntity.ok(bookingService.confirmBooking(id));

    }

    @PutMapping("{id}/cancel")
    ResponseEntity<Booking> cancelBooking(@PathVariable Long id)
    {
        return ResponseEntity.ok(bookingService.cancelBooking(id));

    }

    @GetMapping("/getbookingsbystatus/{status}")
    ResponseEntity<List<Booking>> getBookingsByStatus(@PathVariable BookingStatus status)
    {
        return ResponseEntity.ok(bookingService.getBookingsByStatus(status));
    }


}
