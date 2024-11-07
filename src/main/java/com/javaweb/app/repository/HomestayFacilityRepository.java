package com.javaweb.app.repository;

import com.javaweb.app.entity.HomestayFacilitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomestayFacilityRepository extends JpaRepository<HomestayFacilitiesEntity, Long> {
    List<HomestayFacilitiesEntity> findAll();
}
