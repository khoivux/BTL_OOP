package com.javaweb.app.controller;

import com.javaweb.app.dto.BookingDTO;

import com.javaweb.app.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class BookingController {
    @Autowired
    public BookingService bookingService;

    @PostMapping(value = "/booking")
    public BookingDTO createBooking(@RequestBody BookingDTO bookingDTO) {
        return bookingService.createBooking(bookingDTO);
    }

}
