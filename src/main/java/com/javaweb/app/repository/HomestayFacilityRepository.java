package com.javaweb.app.repository;

import com.javaweb.app.entity.FacilitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomestayFacilityRepository extends JpaRepository<FacilitiesEntity, Long> {
    List<FacilitiesEntity> findAll();
}
