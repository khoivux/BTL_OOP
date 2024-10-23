package com.javaweb.app.service.impl;

import com.javaweb.app.mapper.ProvinceMapper;
import com.javaweb.app.dto.ProvinceDto;

import com.javaweb.app.repository.ProvinceRepository;
import com.javaweb.app.service.ProvinceService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    public ProvinceMapper provinceMapper;
    @Autowired
    public ProvinceRepository provinceRepository;

    @Override
    public ProvinceDto getById(Long id) {
        return provinceMapper.mapToProvinceDto(provinceRepository.getById(id));
    }
}
