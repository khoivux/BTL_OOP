package com.javaweb.app.dto;

import java.util.List;

public class HomestayDto {
    private Long id;
    private String name;
    private Long price;
    private String address;
    private Long provinceid;
    private Long rating;
    private String description;
    private Long numberOfRooms;
    private List<RoomDTO> rooms;
    private List<FacilitiesDTO> facilities;
    public HomestayDto() {
    }

    public HomestayDto(Long id, String name, Long price, String address, Long provinceid, Long rating, String description, Long numberOfRooms, List<RoomDTO> rooms, List<FacilitiesDTO> facilities) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.address = address;
        this.provinceid = provinceid;
        this.rating = rating;
        this.description = description;
        this.numberOfRooms = numberOfRooms;
        this.rooms = rooms;
        this.facilities = facilities;
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

    public void setProvinceid(Long provinceid) {
        this.provinceid = provinceid;
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

    public Long getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Long numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public List<RoomDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDTO> rooms) {
        this.rooms = rooms;
    }

    public List<FacilitiesDTO> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<FacilitiesDTO> facilities) {
        this.facilities = facilities;
    }
}
