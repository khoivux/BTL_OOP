package com.javaweb.app.dto;

import java.util.List;

public class HomestayCreateDTO {
    private Long id;
    private String name;
    private Long price;
    private String address;
    private Long provinceid;
    private Long rating;
    private String description;
    private Long numberOfRooms;
    private Long capacity;
    private List<Long> rooms;
    private List<Long> facilities;
    private List<Long> services;


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

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public List<Long> getRooms() {
        return rooms;
    }

    public void setRooms(List<Long> rooms) {
        this.rooms = rooms;
    }

    public List<Long> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Long> facilities) {
        this.facilities = facilities;
    }

    public List<Long> getServices() {
        return services;
    }

    public void setServices(List<Long> services) {
        this.services = services;
    }
}
