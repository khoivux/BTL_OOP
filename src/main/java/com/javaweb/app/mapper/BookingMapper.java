package com.javaweb.app.mapper;

import com.javaweb.app.entity.BookingEntity;
import com.javaweb.app.dto.BookingDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingMapper {

    public BookingDTO toDTO(BookingEntity bookingEntity) {
        BookingDTO dto = new BookingDTO();
        dto.setId(bookingEntity.getId());
        dto.setCheckInDate(bookingEntity.getCheckInDate());
        dto.setCheckOutDate(bookingEntity.getCheckOutDate());
        dto.setBookingTime(bookingEntity.getBookingTime());
        dto.setStatus(bookingEntity.getStatus());
        dto.setHomestayName(bookingEntity.getHomestay().getName());
        dto.setHomestayPrice(bookingEntity.getHomestay().getPrice());
        dto.setUserFullName(bookingEntity.getUser().getFullName());
        dto.setUserEmail(bookingEntity.getUser().getUserEmail());
        dto.setUserPhone(bookingEntity.getUser().getUserPhone());

        // Map thêm các trường dữ liệu khác nếu cần
        return dto;
    }
}
