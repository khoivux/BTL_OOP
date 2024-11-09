package com.javaweb.app.mapper;

import com.javaweb.app.dto.RoomDTO;
import com.javaweb.app.entity.RoomEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomMapper {
    @Autowired
    private ModelMapper modelMapper;

    public List<RoomDTO> mapToRoomDTOS (List<RoomEntity> entities) {
        if(entities == null) return null;
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for(RoomEntity roomEntity : entities) {
            roomDTOS.add(new RoomDTO(roomEntity.getId(), roomEntity.getType(), roomEntity.getNumbers()));
        }
        return roomDTOS;
    }
    public List<RoomEntity> mapToRoomEntities(List<RoomDTO> roomDTOS) {
        if(roomDTOS == null) return null;
        List<RoomEntity> roomEntities = new ArrayList<>();
        for (RoomDTO roomDTO : roomDTOS) {
            roomEntities.add(new RoomEntity(roomDTO.getId(), roomDTO.getType(), roomDTO.getNumbers()));
        }
        return roomEntities;
    }

}
