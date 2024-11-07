package com.javaweb.app.service.impl;

import com.javaweb.app.dto.HomestayFacilitiesDTO;
import com.javaweb.app.entity.HomestayFacilitiesEntity;
import com.javaweb.app.mapper.HomestayMapper;
import com.javaweb.app.repository.HomestayFacilityRepository;
import com.javaweb.app.service.FacilitiesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FacilityServiceImpl implements FacilitiesService {
    @Autowired
    public HomestayFacilityRepository homestayFacilityRepository;
    @Autowired
    public HomestayMapper homestayMapper;

    @Override
    public List<HomestayFacilitiesDTO> findAll() {
        List<HomestayFacilitiesEntity> entities = homestayFacilityRepository.findAll();
        return homestayMapper.mapToHomestayFacilities(entities);
    }
}
