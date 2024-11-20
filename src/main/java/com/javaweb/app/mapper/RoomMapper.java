package com.javaweb.app.mapper;

import com.javaweb.app.dto.RoomDTO;
import com.javaweb.app.entity.HomestayEntity;
import com.javaweb.app.entity.RoomEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class RoomMapper {
    @Autowired
    private ModelMapper modelMapper;

    // Đẩy lên
    public List<RoomDTO> mapToRoomDTOS (List<RoomEntity> entities) {
        if(entities == null) return null;
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for(RoomEntity roomEntity : entities) {
            RoomDTO roomDTO = new RoomDTO(roomEntity.getType(), roomEntity.getNumbers(), roomEntity.getDescription());
            String imageBase64 = roomEntity.getImage() != null ? Base64.getEncoder().encodeToString(roomEntity.getImage()) : null;
            roomDTO.setImgURL("data:image/jpeg;base64," + imageBase64);

            roomDTOS.add(roomDTO);
        }
        return roomDTOS;
    }
    // Kéo về
    public List<RoomEntity> mapToRoomEntities(List<RoomDTO> roomDTOS, HomestayEntity homestayEntity) {
        if(roomDTOS == null) return null;
        List<RoomEntity> roomEntities = new ArrayList<>();
        for (RoomDTO roomDTO : roomDTOS) {
            RoomEntity roomEntity = new RoomEntity(roomDTO.getType(), roomDTO.getNumbers(), roomDTO.getDescription());
            if (roomDTO.getImage() != null && !roomDTO.getImage().isEmpty()) {
                String fileName = roomDTO.getImage().getOriginalFilename();
                String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

                // Kiểm tra xem file có phải là ảnh với định dạng jpg, png, jpeg không
                if ("jpg".equals(fileExtension) || "png".equals(fileExtension) || "jpeg".equals(fileExtension)) {
                    try {
                        roomEntity.setImage(roomDTO.getImage().getBytes());
                    } catch (IOException e) {
                        e.printStackTrace(); // Log lỗi nếu không thể lấy bytes từ file
                    }
                } else {
                    // Ném lỗi nếu file không phải là ảnh hợp lệ
                    throw new RuntimeException("File không phải ảnh hợp lệ! Chỉ chấp nhận file .jpg, .png hoặc .jpeg");
                }
            }
            roomEntity.setHomestay(homestayEntity);
            roomEntities.add(roomEntity);
        }
        return roomEntities;
    }

}
