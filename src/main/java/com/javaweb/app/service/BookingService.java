package com.javaweb.app.service;

import com.javaweb.app.dto.BookingDTO;

import com.javaweb.app.entity.BookingEntity;


import java.util.List;

public interface BookingService {
    List<BookingEntity> getAllBookings();

    List<BookingEntity> getBookingsByUserId(Long userId);

    List<BookingDTO> getPaymentHistory(Long userId);

    void deleteBooking(Long id);

    BookingDTO createBooking(BookingDTO bookingDTO);

    List<BookingDTO> getBookingbyId(Long id);

    BookingDTO getBookingById(Long bookingId);

    List<BookingDTO> getBookingsByUser_Id(Long userId);

    Long getUserIdByBookingId(Long id);

    BookingDTO getBookingDetails(Long bookingId);

    void updateBookingStatus(Long bookingId, String status);

    BookingDTO findById(Long id);
}
