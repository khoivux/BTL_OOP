package com.javaweb.app.mapper;

import com.javaweb.app.dto.FacilitiesDTO;
import com.javaweb.app.dto.ServiceDTO;
import com.javaweb.app.entity.HomestayEntity;
import com.javaweb.app.dto.HomestayResponseDTO;
import com.javaweb.app.dto.HomestayDto;
import com.javaweb.app.entity.FacilitiesEntity;
import com.javaweb.app.entity.ServiceEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component // Đánh dấu bean
public class HomestayMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    public RoomMapper roomMapper;

    public List<ServiceDTO> mapToServiceDTOs(List<ServiceEntity> entities) {
        List<ServiceDTO> dtos = new ArrayList<>();
        for(ServiceEntity serviceEntity : entities) {
            dtos.add(new ServiceDTO(serviceEntity.getId(), serviceEntity.getName(), serviceEntity.getPrice()));
        }
        return dtos;
    }

    public List<FacilitiesDTO> mapToHomestayFacilities(List<FacilitiesEntity> list) {
        List<FacilitiesDTO> result = new ArrayList<>();
        for(FacilitiesEntity entity : list) {
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
        HomestayEntity homestayEntity = new HomestayEntity();
        homestayEntity.setPrice(homestayEntity.getId());
        homestayEntity.setDescription(homestayDto.getDescription());
        homestayEntity.setRating(homestayDto.getRating());
        homestayEntity.setName(homestayDto.getName());
        homestayEntity.setNumberOfRooms(homestayDto.getNumberOfRooms());
        //homestayEntity.setRooms(roomMapper);
        return homestayEntity;
    }
    public HomestayResponseDTO mapToHomestayResponse(HomestayEntity homestayEntity) {
        HomestayResponseDTO homestayResponse = modelMapper.map(homestayEntity, HomestayResponseDTO.class);
        homestayResponse.setAddress(homestayEntity.getAddress() + ", " + homestayEntity.getProvince().getName());
        homestayResponse.setFacilities(mapToHomestayFacilities(homestayEntity.getFacilities()));
        homestayResponse.setRooms(roomMapper.mapToRoomDTOS(homestayEntity.getRooms()));
        return homestayResponse;
    }
}
