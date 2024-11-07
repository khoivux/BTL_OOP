package com.javaweb.app.service;

import com.javaweb.app.dto.HomestayFacilitiesDTO;


import java.util.List;

public interface FacilitiesService {
    List<HomestayFacilitiesDTO> findAll();
}
