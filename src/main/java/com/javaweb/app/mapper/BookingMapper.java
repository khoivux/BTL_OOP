package com.javaweb.app.mapper;


import com.javaweb.app.entity.BookingEntity;
import com.javaweb.app.dto.BookingDTO;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


@Component
public class BookingMapper {
    @Autowired
    private ModelMapper modelMapper;
    public BookingDTO mapToBookingDTO(BookingEntity bookingEntity) {
        BookingDTO bookingDTO = modelMapper.map(bookingEntity, BookingDTO.class);
        return bookingDTO;
    }
}
