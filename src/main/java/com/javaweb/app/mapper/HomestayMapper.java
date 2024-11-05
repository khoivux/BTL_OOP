package com.javaweb.app.mapper;

import com.javaweb.app.dto.HomestayFacilitiesDTO;
import com.javaweb.app.entity.HomestayEntity;
import com.javaweb.app.dto.HomestayResponseDTO;
import com.javaweb.app.dto.HomestayDto;
import com.javaweb.app.entity.HomestayFacilitiesEntity;
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
    public List<HomestayFacilitiesDTO> mapToHomestayFacilities(List<HomestayFacilitiesEntity> list) {
        List<HomestayFacilitiesDTO> result = new ArrayList<>();
        for(HomestayFacilitiesEntity homestayFacilitiesEntity : list) {
            HomestayFacilitiesDTO homestayFacilitiesDTO = new HomestayFacilitiesDTO();
            homestayFacilitiesDTO.setId(homestayFacilitiesEntity.getId());
            homestayFacilitiesDTO.setName(homestayFacilitiesEntity.getName());
            result.add(homestayFacilitiesDTO);
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
