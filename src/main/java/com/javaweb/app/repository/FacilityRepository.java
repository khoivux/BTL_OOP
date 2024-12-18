package com.javaweb.app.repository;

import com.javaweb.app.entity.FacilitiesEntity;
import com.javaweb.app.entity.HomestayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilityRepository extends JpaRepository<FacilitiesEntity, Long> {
    List<FacilitiesEntity> findAll();
    List<FacilitiesEntity> findByIdIn(List<Long> ids);
}

