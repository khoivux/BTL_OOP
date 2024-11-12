package com.javaweb.app.service.impl;

import com.javaweb.app.dto.ServiceDTO;
import com.javaweb.app.entity.ServiceEntity;
import com.javaweb.app.mapper.HomestayMapper;
import com.javaweb.app.repository.ServiceRepository;
import com.javaweb.app.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    public ServiceRepository serviceRepository;
    @Autowired
    public HomestayMapper homestayMapper;

    public List<ServiceDTO> findAll() {
        List<ServiceEntity> serviceEntities = serviceRepository.findAll();
        return homestayMapper.mapToServiceDTOs(serviceEntities);
    }
}
