package com.example.MovieBookingApplication.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowDTO {
    private LocalDateTime showtime;
    private Double price;
    private Long movieId;
    private Long theaterId;

}
