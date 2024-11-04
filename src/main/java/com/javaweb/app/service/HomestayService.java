package com.javaweb.app.service;

import com.javaweb.app.model.HomestaySearchResponse;
import com.javaweb.app.dto.HomestayDto;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Map;

public interface HomestayService {
    List<HomestaySearchResponse> findAll();

    HomestayDto createHomestay(HomestayDto homestayDto);

    @Transactional
    void deleteHomestays(List<Long> ids);

    void deleteHomestay(Long id);

    HomestayDto findHomestayById(Long id);

    HomestayDto updateHomestay(Long id, HomestayDto updatedHomestayDto);

    List<HomestayDto> findHomestayByIdIn(List<Long> ids);

    List<HomestaySearchResponse> findByFilter(Map<String, Object> params, List<Long> homestayFacilities, List<Long> roomFacilities);
}
