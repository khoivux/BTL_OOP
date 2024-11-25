package com.javaweb.app.service.impl;

import com.javaweb.app.dto.BookingDTO;
import com.javaweb.app.entity.BookingEntity;
import com.javaweb.app.repository.BookingRepository;
import com.javaweb.app.repository.HomestayRepository;
import com.javaweb.app.repository.UserRepository;
import com.javaweb.app.service.BookingService;
import com.javaweb.app.utils.DateUtil;
import com.javaweb.app.utils.MapUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Objects;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    public BookingRepository bookingRepository;
    @Autowired
    public HomestayRepository homestayRepository;
    @Autowired
    public UserRepository userRepository;

    public BookingDTO createBooking(Map<String, Object> params,
                                    Long userId,
                                    HttpSession session) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setUser(userRepository.getById(userId));
        bookingDTO.setCustomerName(MapUtil.getObject(params, "customerName", String.class));
        bookingDTO.setCustomerEmail(MapUtil.getObject(params, "customerEmail", String.class));
        bookingDTO.setCustomerPhone(MapUtil.getObject(params, "customerPhone", String.class));
        bookingDTO.setHomestay(homestayRepository.getById(Objects.requireNonNull(MapUtil.getObject(params, "homestayId", Long.class))));
        bookingDTO.setStatus("Đang thực hiện");
        bookingDTO.setCheckInDate(DateUtil.strToDate(MapUtil.getObject(params, "checkInDate", String.class)));
        bookingDTO.setCheckOutDate(DateUtil.strToDate(MapUtil.getObject(params, "checkOutDate", String.class)));
        bookingDTO.setBookingTime(LocalDateTime.now());

        long daysBetween = ChronoUnit.DAYS.between(bookingDTO.getCheckInDate(), bookingDTO.getCheckOutDate());
        bookingDTO.setStayDuration(daysBetween);
        bookingDTO.setTotal(daysBetween * bookingDTO.getHomestay().getPrice());

        return bookingDTO;
    }
}
