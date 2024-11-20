package com.javaweb.app.service;

import com.javaweb.app.dto.BookingDTO;

import java.util.Map;

public interface BookingService {
    BookingDTO createBooking(Map<String, Object> params);
}
