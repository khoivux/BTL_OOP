package com.javaweb.app.service;

import com.javaweb.app.dto.FacilitiesDTO;


import java.util.List;

public interface FacilitiesService {
    List<FacilitiesDTO> findAll();
    List<FacilitiesDTO> findListByIds(List<Long> ids);
}
