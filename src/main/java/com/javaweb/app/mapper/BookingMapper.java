package com.javaweb.app.mapper;

import com.javaweb.app.entity.BookingEntity;
import com.javaweb.app.dto.BookingDTO;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingDTO toDTO(BookingEntity bookingEntity) {
        BookingDTO dto = new BookingDTO();
        dto.setId(bookingEntity.getId());
        dto.setCheckInDate(bookingEntity.getCheckInDate());
        dto.setCheckOutDate(bookingEntity.getCheckOutDate());
        dto.setBookingTime(bookingEntity.getBookingTime());
        dto.setStatus(bookingEntity.getStatus());
        // Map thêm các trường dữ liệu khác nếu cần
        return dto;
    }
}