package com.javaweb.app.mapper;

import com.javaweb.app.entity.ProvinceEntity;
import com.javaweb.app.dto.ProvinceDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProvinceMapper {
    @Autowired
    private ModelMapper modelMapper;

    public ProvinceDto mapToProvinceDto(ProvinceEntity provinceEntity) {
        return modelMapper.map(provinceEntity, ProvinceDto.class);
    }

    public ProvinceEntity mapToProvinceEntity(ProvinceDto provinceDto) {
        return modelMapper.map(provinceDto, ProvinceEntity.class);
    }
}
