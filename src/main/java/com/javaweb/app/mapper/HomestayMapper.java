package com.javaweb.app.mapper;

import com.javaweb.app.entity.HomestayEntity;
import com.javaweb.app.model.HomestaySearchResponse;
import com.javaweb.app.dto.HomestayDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // Đánh dấu bean
public class HomestayMapper {
    @Autowired
    private ModelMapper modelMapper;

    public HomestayDto mapToHomestayDto(HomestayEntity homestayEntity) {
        HomestayDto homestayDto = modelMapper.map(homestayEntity, HomestayDto.class);
        homestayDto.setProvinceid(homestayEntity.getProvince().getId());
        return homestayDto;
    }

    public HomestayEntity mapToHomestayEntity(HomestayDto homestayDto) {
        return modelMapper.map(homestayDto, HomestayEntity.class);
    }

    public HomestaySearchResponse mapToHomestayResponse(HomestayEntity homestayEntity) {
        HomestaySearchResponse homestayResponse = modelMapper.map(homestayEntity, HomestaySearchResponse.class);
        homestayResponse.setAddress(homestayEntity.getAddress() + ", " + homestayEntity.getProvince().getName());
        return homestayResponse;
    }
}
