package com.javaweb.app.repository;


import com.javaweb.app.entity.HomestayEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HomestayRepository extends HomestayRepositoryCustom, JpaRepository<HomestayEntity, Long> {
    @NonNull
    List<HomestayEntity> findAll();

    Optional<HomestayEntity> findById(Long id);
    void deleteByIdIn(List<Long> ids);

    List<HomestayEntity> findByIdIn(List<Long> ids);

//    List<HomestayEntity> findByIdAndNameContainingAndAddressContaining(Long id, String name, String address);
}
