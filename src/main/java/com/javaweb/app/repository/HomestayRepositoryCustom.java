package com.javaweb.app.repository;

import com.javaweb.app.entity.HomestayEntity;
import com.javaweb.app.model.HomestaySearchRequest;

import java.util.List;

public interface HomestayRepositoryCustom {
    List<HomestayEntity> findByFilter(HomestaySearchRequest homestaySearchRequest, List<Long> homestayFacilities);
}
