package com.javaweb.app.repository;

import com.javaweb.app.entity.ProvinceEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<ProvinceEntity, Long> {
    @NonNull
    ProvinceEntity getById(Long id);
}
