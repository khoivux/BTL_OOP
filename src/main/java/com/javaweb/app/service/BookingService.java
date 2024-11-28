package com.javaweb.app.service;

import com.javaweb.app.dto.BookingDTO;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

import com.javaweb.app.entity.BookingEntity;


import java.util.List;

public interface BookingService {
    List<BookingEntity> getAllBookings();

    List<BookingEntity> getBookingsByUserId(Long userId);

    List<BookingDTO> getPaymentHistory(Long userId);

    void deleteBooking(Long id);


    List<BookingDTO> getBookingsByUser_Id(Long userId);

    Long getUserIdByBookingId(Long id);

    void updateBookingStatus(Long bookingId, String status);

    BookingDTO findById(Long id);

    BookingDTO createBooking(Map<String, Object> params, Long userId, HttpSession session);

}
