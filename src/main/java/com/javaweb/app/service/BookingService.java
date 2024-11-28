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

    void deleteBookingById(Long id);
    void cancelBookingById(Long id);

    List<BookingDTO> getPaymentHistory(Long userId);



    List<BookingDTO> getBookingsByUser_Id(Long userId);

    Long getUserIdByBookingId(Long id);

    void updateBookingStatus(Long bookingId, String status);

    BookingDTO findById(Long id);



    BookingDTO createBooking(Map<String, Object> params, Long userId, HttpSession session);
    void validDateBooking(Long homestayId, LocalDate checkInDate, LocalDate checkOutDate);
    BookingDTO saveBooking(BookingDTO bookingDTO);


    List<BookingEntity> getBookingsByUserId(Long userId);
}
