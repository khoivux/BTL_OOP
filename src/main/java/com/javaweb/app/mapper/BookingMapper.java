package com.javaweb.app.mapper;

import com.javaweb.app.dto.BookingDTO;
import com.javaweb.app.dto.HomestayDto;
import com.javaweb.app.entity.BookingEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class BookingMapper {
    @Autowired
    private ModelMapper modelMapper;
    public BookingDTO mapToBookingDTO(BookingEntity bookingEntity) {
        BookingDTO bookingDTO = modelMapper.map(bookingEntity, BookingDTO.class);
        return bookingDTO;
    }
}
