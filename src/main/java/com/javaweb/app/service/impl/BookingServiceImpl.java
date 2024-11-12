package com.javaweb.app.service.impl;

import com.javaweb.app.dto.BookingDTO;
import com.javaweb.app.entity.BookingEntity;
import com.javaweb.app.repository.BookingRepository;
import com.javaweb.app.repository.HomestayRepository;
import com.javaweb.app.repository.UserRepository;
import com.javaweb.app.service.BookingService;
import com.javaweb.app.mapper.BookingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service // Thêm annotation @Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    // Inject BookingRepository và BookingMapper
    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }
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

    @Override
    public List<BookingEntity> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public List<BookingEntity> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUser_Id(userId);
    }

    @Override
    public List<BookingDTO> getPaymentHistory(Long userId) {
        // Lấy danh sách các BookingEntity từ DB
        int a=1;

        List<BookingEntity> bookings = bookingRepository.findByUser_Id(userId);
        if (bookings.isEmpty()) {
            return Collections.emptyList();
        }
        int cnt=0;
        // Chuyển đổi thành BookingDTO
        return bookings.stream()
                .map(bookingMapper::toDTO) // Dùng BookingMapper để chuyển đổi
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
