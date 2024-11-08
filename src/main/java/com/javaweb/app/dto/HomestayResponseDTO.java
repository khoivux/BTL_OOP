package com.javaweb.app.dto;

import java.util.Comparator;
import java.util.List;

public class HomestayResponseDTO {
    private Long id;
    private String name;
    private String address;
    private Long price;
    private Long rating;
    private String description;
    private List<RoomDTO> rooms;
    private List<FacilitiesDTO> facilities;
    private List<ServiceDTO> services;


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

    public List<FacilitiesDTO> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<FacilitiesDTO> facilities) {
        this.facilities = facilities;
    }

    public List<ServiceDTO> getServices() {
        return services;
    }

    public void setServices(List<ServiceDTO> services) {
        this.services = services;
    }

    public static Comparator<HomestayResponseDTO> priceAsc = new Comparator<HomestayResponseDTO>() {
        @Override
        public int compare(HomestayResponseDTO h1, HomestayResponseDTO h2) {
            return Double.compare(h1.price, h2.price); // Tăng dần
        }
    };

    public static Comparator<HomestayResponseDTO> priceDesc = new Comparator<HomestayResponseDTO>() {
        @Override
        public int compare(HomestayResponseDTO h1, HomestayResponseDTO h2) {
            return Double.compare(h2.price, h1.price); // Giảm dần
        }
    };
}
