package com.javaweb.app.mapper;

import com.javaweb.app.dto.*;
import com.javaweb.app.entity.HomestayEntity;
import com.javaweb.app.entity.FacilitiesEntity;
import com.javaweb.app.entity.ServiceEntity;

import com.javaweb.app.exception.FileNotValidException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@Component // Đánh dấu bean
public class HomestayMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    public RoomMapper roomMapper;
    @Autowired
    public ProvinceMapper provinceMapper;
    public List<ServiceDTO> mapToServiceDTOs(List<ServiceEntity> entities) {
        if(entities == null) return null;
        List<ServiceDTO> dtos = new ArrayList<>();
        for(ServiceEntity serviceEntity : entities) {
            dtos.add(new ServiceDTO(serviceEntity.getId(), serviceEntity.getName(), serviceEntity.getPrice()));
        }
        return dtos;
    }

    public List<FacilitiesDTO> mapToHomestayFacilities(List<FacilitiesEntity> entities) {
        if(entities == null) return null;
        List<FacilitiesDTO> result = new ArrayList<>();
        for(FacilitiesEntity entity : entities) {
            FacilitiesDTO dto = new FacilitiesDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            result.add(dto);
        }
        return result;
    }
    public HomestayDto mapToHomestayDto(HomestayEntity homestayEntity) {
        HomestayDto homestayDto = modelMapper.map(homestayEntity, HomestayDto.class);
        homestayDto.setProvinceid(homestayEntity.getProvince().getId());
        return homestayDto;
    }

    public HomestayEntity mapToHomestayEntity(HomestayDto homestayDto) {
        HomestayEntity homestayEntity = modelMapper.map(homestayDto, HomestayEntity.class);
        return homestayEntity;
    }
    public HomestayResponseDTO mapToHomestayResponse(HomestayEntity homestayEntity) {
        HomestayResponseDTO homestayResponse = modelMapper.map(homestayEntity, HomestayResponseDTO.class);
        homestayResponse.setProvince(provinceMapper.mapToProvinceDto(homestayEntity.getProvince()));
        homestayResponse.setFacilities(mapToHomestayFacilities(homestayEntity.getFacilities()));
        homestayResponse.setRooms(roomMapper.mapToRoomDTOS(homestayEntity.getRooms()));
        String imageBase64 = homestayEntity.getImage() != null ? Base64.getEncoder().encodeToString(homestayEntity.getImage()) : null;
        homestayResponse.setImage("data:image/jpeg;base64," + imageBase64);
        return homestayResponse;
    }

    public HomestayEntity mapToSavedHomestayEntity(HomestayCreateDTO homestayCreateDTO) {
        HomestayEntity homestayEntity = modelMapper.map(homestayCreateDTO, HomestayEntity.class);
        homestayEntity.setRating(5L);
        if (homestayCreateDTO.getImage() != null && !homestayCreateDTO.getImage().isEmpty()) {
            String fileName = homestayCreateDTO.getImage().getOriginalFilename();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            if ("jpg".equals(fileExtension) || "png".equals(fileExtension) || "jpeg".equals(fileExtension)) {
                try {
                    homestayEntity.setImage(homestayCreateDTO.getImage().getBytes());
                } catch (IOException e) {
                    throw new FileNotValidException("Lỗi khi xử lý file ảnh!");
                }
            } else {
                throw new FileNotValidException("File không hợp lệ! Chỉ chấp nhận file .jpg, .png hoặc .jpeg");
            }
        }
        return homestayEntity;
    }
}
