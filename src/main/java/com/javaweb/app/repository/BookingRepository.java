package com.javaweb.app.repository;

import com.javaweb.app.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    List<BookingEntity> findAll();
    List<BookingEntity> findByUser_Id(Long userId);
}
