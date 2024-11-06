package com.javaweb.app.dto;

import java.util.List;

public class HomestayResponseDTO {
    private Long id;
    private String name;
    private String address;
    private Long price;
    private Long rating;
    private String description;
    private List<RoomDTO> rooms;
    private List<HomestayFacilitiesDTO> facilities;
    private List<ServiceDTO> services;
    public HomestayResponseDTO() {
    }

    public HomestayResponseDTO(Long id, String name, String address, Long price, Long rating, String description, List<RoomDTO> rooms, List<HomestayFacilitiesDTO> facilities, List<ServiceDTO> services) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.rooms = rooms;
        this.facilities = facilities;
        this.services = services;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RoomDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDTO> rooms) {
        this.rooms = rooms;
    }

    public List<HomestayFacilitiesDTO> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<HomestayFacilitiesDTO> facilities) {
        this.facilities = facilities;
    }

    public List<ServiceDTO> getServices() {
        return services;
    }

    public void setServices(List<ServiceDTO> services) {
        this.services = services;
    }
}
