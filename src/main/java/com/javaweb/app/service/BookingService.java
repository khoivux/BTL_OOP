package com.javaweb.app.service;

import com.javaweb.app.dto.BookingDTO;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.Map;

import com.javaweb.app.entity.BookingEntity;

import java.util.List;

public interface BookingService {
    List<BookingDTO> getPaymentHistory(Long userId);
    BookingDTO createBooking(Map<String, Object> params, Long userId, HttpSession session);
    void validDateBooking(Long homestayId, LocalDate checkInDate, LocalDate checkOutDate);
    BookingDTO saveBooking(BookingDTO bookingDTO);
}
