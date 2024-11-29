package com.javaweb.app.service;

import com.javaweb.app.dto.RoomDTO;
import com.javaweb.app.entity.RoomEntity;

import java.util.List;

public interface RoomService {
    List<RoomDTO> findAll();
    void updateRooms(List<RoomDTO> roomDTOS, List<RoomEntity> roomEntities);
}
