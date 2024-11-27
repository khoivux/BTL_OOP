package com.javaweb.app.service.impl;

import com.javaweb.app.dto.HomestayCreateDTO;
import com.javaweb.app.exception.FileNotValidException;
import com.javaweb.app.entity.FacilitiesEntity;
import com.javaweb.app.entity.HomestayEntity;
import com.javaweb.app.entity.ProvinceEntity;
import com.javaweb.app.mapper.HomestayMapper;
import com.javaweb.app.mapper.HomestayRequestMapper;
import com.javaweb.app.dto.HomestaySearchRequestDTO;
import com.javaweb.app.dto.HomestayResponseDTO;
import com.javaweb.app.mapper.RoomMapper;
import com.javaweb.app.repository.FacilityRepository;
import com.javaweb.app.repository.HomestayRepository;
import com.javaweb.app.repository.ProvinceRepository;
import com.javaweb.app.service.HomestayService;
import com.javaweb.app.service.RoomService;
import com.javaweb.app.utils.MapUtil;
import com.javaweb.app.utils.StringUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    @Autowired
    private RoomService roomService;

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
    @Override // Lấy 1 Homestay theo id
    public HomestayResponseDTO findHomestayById(Long id) {
        HomestayEntity homestayEntity = homestayRepository.getById(id);
        return homestayMapper.mapToHomestayResponse(homestayEntity);
    }

    // UPDATE
    @Override // Cập nhật thông tin một Homestay ở DB
    public void updateHomestay(HomestayCreateDTO updatedHomestayDto) {
        List<FacilitiesEntity> facilitiesEntities = (updatedHomestayDto.getFacilities() == null) ? null : facilityRepository.findAllById(updatedHomestayDto.getFacilities());
        HomestayEntity homestayEntity = homestayRepository.findById(updatedHomestayDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Không thể tìm thấy homestay có id: " + updatedHomestayDto.getId()));
        ProvinceEntity provinceEntity = provinceRepository.getById(updatedHomestayDto.getProvinceid());

        roomService.updateRooms(updatedHomestayDto.getRooms(), homestayEntity.getRooms());

        homestayEntity.setName(updatedHomestayDto.getName());
        homestayEntity.setAddress(updatedHomestayDto.getAddress());
        homestayEntity.setPrice(updatedHomestayDto.getPrice());
        homestayEntity.setFacilities(facilitiesEntities);
        homestayEntity.setProvince(provinceEntity);
        homestayEntity.setDescription(updatedHomestayDto.getDescription());

        if(updatedHomestayDto.getImage() != null && !updatedHomestayDto.getImage().isEmpty()) {
            String fileName = updatedHomestayDto.getImage().getOriginalFilename();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            if ("jpg".equals(fileExtension) || "png".equals(fileExtension) || "jpeg".equals(fileExtension)) {
                try {
                    homestayEntity.setImage(updatedHomestayDto.getImage().getBytes());
                } catch (IOException e) {
                    throw new FileNotValidException("Lỗi khi xử lý file ảnh!");
                }
            } else {
                throw new FileNotValidException("File không hợp lệ! Chỉ chấp nhận file .jpg, .png hoặc .jpeg");
            }
        }
        homestayRepository.save(homestayEntity);
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



    @Override // Xóa 1 homestay theo id
    public void deleteHomestay(Long id) {
        homestayRepository.deleteById(id);
    }
}
