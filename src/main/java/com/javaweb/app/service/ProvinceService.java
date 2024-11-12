package com.javaweb.app.service;

import com.javaweb.app.dto.ProvinceDto;

import java.util.List;

public interface ProvinceService {
    ProvinceDto getById(Long id);
    List<ProvinceDto> findAll();
}
