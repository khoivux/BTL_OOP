package com.javaweb.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class HomestayCreateDTO {
    private Long id;
    private String name;
    private Long price;
    private String address;
    private Long provinceid;
    private String description;
    private Long capacity;
    private List<RoomDTO> rooms;
    private List<Long> facilities;
    @JsonIgnore
    private MultipartFile image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getProvinceid() {
        return provinceid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public void setProvinceid(Long provinceid) {
        this.provinceid = provinceid;
    }

    public List<Long> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Long> facilities) {
        this.facilities = facilities;
    }

    public List<RoomDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDTO> rooms) {
        this.rooms = rooms;
    }


    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
