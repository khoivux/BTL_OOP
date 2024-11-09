package com.javaweb.app.service.impl;

import com.javaweb.app.dto.BookingDTO;
import com.javaweb.app.entity.BookingEntity;
import com.javaweb.app.repository.BookingRepository;
import com.javaweb.app.repository.HomestayRepository;
import com.javaweb.app.repository.UserRepository;
import com.javaweb.app.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    public BookingRepository bookingRepository;
    @Autowired
    public HomestayRepository homestayRepository;
    @Autowired
    public UserRepository userRepository;

    public BookingDTO createBooking(BookingDTO bookingDTO) {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setUser(userRepository.getById(bookingDTO.getUser_id()));
        bookingEntity.setHomestay(homestayRepository.getById(bookingDTO.getHomestay_id()));
        bookingEntity.setStatus("Đã cọc");
        bookingEntity.setCheckInDate(bookingDTO.getCheckInDate());
        bookingEntity.setCheckOutDate(bookingDTO.getCheckOutDate());
        bookingEntity.setBookingTime(LocalDateTime.now());
        bookingRepository.save(bookingEntity);
        return bookingDTO;
    }
}
