package com.javaweb.app.service;

import com.javaweb.app.dto.RoomDTO;

import java.util.List;

public interface RoomService {
    List<RoomDTO> findAll();
}
