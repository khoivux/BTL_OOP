package com.javaweb.app.service;

import com.javaweb.app.dto.BookingDTO;

import com.javaweb.app.dto.HomestayDto;
import com.javaweb.app.entity.BookingEntity;


import java.util.List;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;

import java.util.Map;

import java.util.List;

public interface BookingService {
    List<BookingDTO> findBookingByUserId(Long userId);
    BookingDTO saveBooking(BookingDTO bookingDTO);
    List<BookingDTO> getPaymentHistory(Long userId);
    BookingDTO findById(Long id);
    BookingDTO createBooking(Map<String, Object> params, Long userId, HttpSession session);
    void validDateBooking(Long homestayId, LocalDate checkInDate, LocalDate checkOutDate);
    void updateBookingStatus(Long bookingId, String status);
    void deleteBookingById(Long id);
    void cancelBookingById(Long id);
}
