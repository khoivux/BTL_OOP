package com.javaweb.app.mapper;

import com.javaweb.app.dto.*;
import com.javaweb.app.entity.HomestayEntity;
import com.javaweb.app.entity.FacilitiesEntity;
import com.javaweb.app.entity.ServiceEntity;

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
        homestayResponse.setAddress(homestayEntity.getAddress() + ", " + homestayEntity.getProvince().getName());
        homestayResponse.setFacilities(mapToHomestayFacilities(homestayEntity.getFacilities()));
        //homestayResponse.setRooms(roomMapper.mapToRoomDTOS(homestayEntity.getRooms()));
        String imageBase64 = homestayEntity.getImage() != null ? Base64.getEncoder().encodeToString(homestayEntity.getImage()) : null;
        homestayResponse.setImage("data:image/jpeg;base64," + imageBase64);
        return homestayResponse;
    }

    public HomestayEntity mapToSavedHomestayEntity(HomestayCreateDTO homestayCreateDTO) {
        HomestayEntity homestayEntity = new HomestayEntity();
        homestayEntity.setId(15L);
        homestayEntity.setName(homestayCreateDTO.getName());
        homestayEntity.setPrice(homestayCreateDTO.getPrice());
        homestayEntity.setRating(6L);
        homestayEntity.setDescription(homestayCreateDTO.getDescription());
        homestayEntity.setAddress(homestayCreateDTO.getAddress());
        if (homestayCreateDTO.getImage() != null && !homestayCreateDTO.getImage().isEmpty()) {
            try {
                homestayEntity.setImage(homestayCreateDTO.getImage().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return homestayEntity;
    }
}
