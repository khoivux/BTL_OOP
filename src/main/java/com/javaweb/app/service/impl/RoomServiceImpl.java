package com.javaweb.app.service.impl;

import com.javaweb.app.dto.RoomDTO;
import com.javaweb.app.entity.RoomEntity;
import com.javaweb.app.exception.FileNotValidException;
import com.javaweb.app.mapper.RoomMapper;
import com.javaweb.app.repository.RoomRepository;
import com.javaweb.app.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    public RoomMapper roomMapper;
    @Autowired
    public RoomRepository roomRepository;
    @Override
    public List<RoomDTO> findAll() {
        return List.of(null);
    }

    @Override
    public void updateRooms(List<RoomDTO> roomDTOS, List<RoomEntity> roomEntities) {
        for(int i = 0; i < roomEntities.size(); i++) {
            roomEntities.get(i).setDescription(roomDTOS.get(i).getDescription());
            roomEntities.get(i).setType(roomDTOS.get(i).getType());
            roomEntities.get(i).setNumbers(roomDTOS.get(i).getNumbers());
            if (roomDTOS.get(i).getImage() != null && !roomDTOS.get(i).getImage().isEmpty()) {
                String fileName = roomDTOS.get(i).getImage().getOriginalFilename();
                String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

                if ("jpg".equals(fileExtension) || "png".equals(fileExtension) || "jpeg".equals(fileExtension)) {
                    try {
                        roomEntities.get(i).setImage(roomDTOS.get(i).getImage().getBytes());
                    } catch (IOException e) {
                        throw new FileNotValidException("Lỗi khi xử lý file ảnh!");
                    }
                } else {
                    throw new FileNotValidException("File không phải ảnh hợp lệ! Chỉ chấp nhận file .jpg, .png hoặc .jpeg");
                }
            }
            roomRepository.save(roomEntities.get(i));
        }
    }
}
