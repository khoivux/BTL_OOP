package com.javaweb.app.service.impl;

import com.javaweb.app.dto.FacilitiesDTO;
import com.javaweb.app.entity.FacilitiesEntity;
import com.javaweb.app.mapper.HomestayMapper;
import com.javaweb.app.repository.HomestayFacilityRepository;
import com.javaweb.app.service.FacilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityServiceImpl implements FacilitiesService {
    @Autowired
    public HomestayFacilityRepository homestayFacilityRepository;
    @Autowired
    public HomestayMapper homestayMapper;

    @Override
    public List<FacilitiesDTO> findAll() {
        List<FacilitiesEntity> entities = homestayFacilityRepository.findAll();
        return homestayMapper.mapToHomestayFacilities(entities);
    }
}
