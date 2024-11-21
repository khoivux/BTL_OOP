package com.javaweb.app.repository;

import com.javaweb.app.entity.HomestayEntity;
import com.javaweb.app.dto.HomestaySearchRequestDTO;

import java.util.List;

public interface HomestayRepositoryCustom {

    List<HomestayEntity> findByFilter(HomestaySearchRequestDTO homestaySearchRequestDTO);
}
