package com.javaweb.app.repository;

import com.javaweb.app.entity.HomestayEntity;
import com.javaweb.app.dto.HomestayRequestDTO;

import java.util.List;

public interface HomestayRepositoryCustom {
    List<HomestayEntity> findByFilter(HomestayRequestDTO homestayRequestDTO);
}
