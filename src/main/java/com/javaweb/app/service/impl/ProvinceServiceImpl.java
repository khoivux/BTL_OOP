package com.javaweb.app.service.impl;

import com.javaweb.app.entity.ProvinceEntity;
import com.javaweb.app.mapper.ProvinceMapper;
import com.javaweb.app.dto.ProvinceDto;

import com.javaweb.app.repository.ProvinceRepository;
import com.javaweb.app.service.ProvinceService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    public ProvinceMapper provinceMapper;
    @Autowired
    public ProvinceRepository provinceRepository;
    @Override
    public List<ProvinceDto> findAll() {
        List<ProvinceEntity> provinceEntities = provinceRepository.findAll();
        List<ProvinceDto> provinceDtos = new ArrayList<>();
        for(ProvinceEntity provinceEntity : provinceEntities) {
            provinceDtos.add(new ProvinceDto(provinceEntity.getId(), provinceEntity.getName()));
        }
        return provinceDtos;
    }
}
