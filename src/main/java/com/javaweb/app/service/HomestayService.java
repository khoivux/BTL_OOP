package com.javaweb.app.service;

import com.javaweb.app.dto.HomestayCreateDTO;
import com.javaweb.app.dto.HomestayResponseDTO;
import com.javaweb.app.dto.HomestayDto;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface HomestayService {
    List<HomestayResponseDTO> findAll();
    HomestayResponseDTO createHomestay(HomestayCreateDTO homestayCreateDTO);
    List<HomestayResponseDTO> findByFilter(Map<String, Object> params,
                                           List<Long> homestayFacilities);
    HomestayResponseDTO findHomestayById(Long id);
    void deleteHomestay(Long id);
    void updateHomestay(HomestayCreateDTO updatedHomestayDto);
}
