package com.javaweb.app.service;

import com.javaweb.app.dto.BookingDTO;
import com.javaweb.app.dto.HomestayDto;
import com.javaweb.app.entity.BookingEntity;

import java.util.List;
import java.util.Map;

public interface BookingService {
    BookingDTO createBooking(Map<String, Object> params, Long userId);
    List<BookingDTO> findBookingByUserId(Long userId);
    BookingDTO findById(Long id);
    void deleteBookingById(Long id);
    void cancelBookingById(Long id);
}
