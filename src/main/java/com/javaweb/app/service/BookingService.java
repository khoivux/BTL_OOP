package com.javaweb.app.service;

import com.javaweb.app.dto.BookingDTO;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.Map;

public interface BookingService {
    BookingDTO createBooking(Map<String, Object> params, Long userId, HttpSession session);
    void validDateBooking(Long homestayId, LocalDate checkInDate, LocalDate checkOutDate);
    BookingDTO saveBooking(BookingDTO bookingDTO);
}
