package com.javaweb.app.service.impl;

import com.javaweb.app.dto.HomestayCreateDTO;
import com.javaweb.app.entity.FacilitiesEntity;
import com.javaweb.app.entity.HomestayEntity;
import com.javaweb.app.entity.ProvinceEntity;
import com.javaweb.app.mapper.HomestayMapper;
import com.javaweb.app.mapper.HomestayRequestMapper;
import com.javaweb.app.dto.HomestaySearchRequestDTO;
import com.javaweb.app.dto.HomestayResponseDTO;
import com.javaweb.app.dto.HomestayDto;
import com.javaweb.app.mapper.RoomMapper;
import com.javaweb.app.repository.FacilityRepository;
import com.javaweb.app.repository.HomestayRepository;
import com.javaweb.app.repository.ProvinceRepository;
import com.javaweb.app.service.HomestayService;
import com.javaweb.app.utils.MapUtil;
import com.javaweb.app.utils.StringUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class HomestayServiceImpl implements HomestayService {
    @Autowired
    private HomestayRepository homestayRepository;
    @Autowired
    private HomestayMapper homestayMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private HomestayRequestMapper homestayRequestMapper;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private FacilityRepository facilityRepository;

    // CREATE
    @Override   // Thêm mới 1 Homestay mới
    public HomestayResponseDTO createHomestay(HomestayCreateDTO homestayCreateDTO) {
        List<FacilitiesEntity> facilitiesEntities = (homestayCreateDTO.getFacilities() == null) ? null : facilityRepository.findAllById(homestayCreateDTO.getFacilities());
        ProvinceEntity provinceEntity = provinceRepository.getById(homestayCreateDTO.getProvinceid());

        HomestayEntity homestayEntity = homestayMapper.mapToSavedHomestayEntity(homestayCreateDTO);
        homestayEntity.setRooms(roomMapper.mapToRoomEntities(homestayCreateDTO.getRooms(), homestayEntity));
        homestayEntity.setFacilities(facilitiesEntities);
        homestayEntity.setProvince(provinceEntity);
        homestayRepository.save(homestayEntity);
        return homestayMapper.mapToHomestayResponse(homestayEntity);
    }

    // READ
    @Override // Lấy tất cả Homestay có trong DB
    public List<HomestayResponseDTO> findAll() {
        List<HomestayEntity> homestayEntities = homestayRepository.findAll();
        List<HomestayResponseDTO> homestayResponsDTOS = new ArrayList<>();
        for (HomestayEntity homestayEntity : homestayEntities) {
            homestayResponsDTOS.add(homestayMapper.mapToHomestayResponse(homestayEntity));
        }
        return homestayResponsDTOS;
    }

    @Override
    public List<HomestayDto> findHomestayByIdIn(List<Long> ids) {
        List<HomestayEntity> homestayEntities = homestayRepository.findByIdIn(ids);
        List<HomestayDto> homestayDtos = new ArrayList<>();
        for (HomestayEntity homestayEntity : homestayEntities) {
            homestayDtos.add(homestayMapper.mapToHomestayDto(homestayEntity));
        }
        return homestayDtos;
    }

    @Override // Lấy tất cả Homestay được lọc theo Filter
    public List<HomestayResponseDTO> findByFilter(Map<String, Object> params,
                                                  List<Long> homestayFacilities) {
        HomestaySearchRequestDTO homestaySearchRequestDTO = homestayRequestMapper.mapToHomestayRequest(params, homestayFacilities);
        List<HomestayEntity> homestayEntities = homestayRepository.findByFilter(homestaySearchRequestDTO);
        List<HomestayResponseDTO> result = new ArrayList<>();
        for (HomestayEntity homestayEntity : homestayEntities) {
            result.add(homestayMapper.mapToHomestayResponse(homestayEntity));
        }
        String sort = MapUtil.getObject(params, "sort", String.class);
        if(StringUtil.isValid(sort)) {
            Comparator<HomestayResponseDTO> comparator = sort.equals("desc") ? HomestayResponseDTO.priceDesc : HomestayResponseDTO.priceAsc;
            // Sắp xếp danh sách homestay theo giá
            result.sort(comparator);
        }
        return result;
    }

    @Override // Lấy 1 Homestay theo id
    public HomestayResponseDTO findHomestayById(Long id) {
        HomestayEntity homestayEntity = homestayRepository.getById(id);
        return homestayMapper.mapToHomestayResponse(homestayEntity);
    }

    // UPDATE
    @Override // Cập nhật thông tin một Homestay ở DB
    public HomestayDto updateHomestay(Long id, HomestayDto updatedHomestayDto) {
        HomestayEntity homestayEntity = homestayRepository.getById(id); //Optional
        homestayEntity.setName(updatedHomestayDto.getName());
        homestayEntity.setAddress(updatedHomestayDto.getAddress());
        homestayEntity.setRating(updatedHomestayDto.getRating());
        homestayEntity.setProvince(provinceRepository.getById(updatedHomestayDto.getProvinceid()));
        homestayEntity.setDescription(updatedHomestayDto.getDescription());
        homestayRepository.save(homestayEntity);
        return homestayMapper.mapToHomestayDto(homestayEntity);
    }

    // DELETE
    @Override
    @Transactional // Xóa nhiều Homestay theo danh sách id
    public void deleteHomestays(List<Long> ids) {
        homestayRepository.deleteByIdIn(ids);
    }

    @Override // Xóa 1 homestay theo id
    public void deleteHomestay(Long id) {
        homestayRepository.deleteById(id);
    }
}
