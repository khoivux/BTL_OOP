package com.javaweb.app.service.impl;

import com.javaweb.app.dto.FacilitiesDTO;
import com.javaweb.app.entity.FacilitiesEntity;
import com.javaweb.app.mapper.HomestayMapper;
import com.javaweb.app.repository.FacilityRepository;
import com.javaweb.app.service.FacilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityServiceImpl implements FacilitiesService {
    @Autowired
    public FacilityRepository facilityRepository;
    @Autowired
    public HomestayMapper homestayMapper;

    @Override
    public List<FacilitiesDTO> findAll() {
        List<FacilitiesEntity> entities = facilityRepository.findAll();
        return homestayMapper.mapToHomestayFacilities(entities);
    }

    @Override
    public List<FacilitiesDTO> findListByIds(List<Long> ids) {
        List<FacilitiesEntity> facilitiesEntities = facilityRepository.findByIdIn(ids);
        return homestayMapper.mapToHomestayFacilities(facilitiesEntities);
    }
}
