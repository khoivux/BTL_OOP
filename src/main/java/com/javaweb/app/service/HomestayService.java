package com.javaweb.app.service;

import com.javaweb.app.dto.HomestayResponseDTO;
import com.javaweb.app.dto.HomestayDto;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface HomestayService {
    List<HomestayResponseDTO> findAll();

    HomestayResponseDTO createHomestay(Map<String, Object> params, List<Long> facilities, List<Long> services);

    @Transactional
    void deleteHomestays(List<Long> ids);

    void deleteHomestay(Long id);

    HomestayResponseDTO findHomestayById(Long id);

    HomestayDto updateHomestay(Long id, HomestayDto updatedHomestayDto);


    List<HomestayDto> findHomestayByIdIn(List<Long> ids);

    List<HomestayResponseDTO> findByFilter(Map<String, Object> params, List<Long> homestayFacilities, List<Long> rooms, List<Long> services);
}
