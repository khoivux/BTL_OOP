package com.javaweb.app.dto;


import java.util.Comparator;

import java.util.List;

public class HomestayResponseDTO {
    private Long id;
    private String name;
    private String address;
    private ProvinceDto province;
    private Long price;
    private Long capacity;
    private String description;
    private List<RoomDTO> rooms;
    private List<FacilitiesDTO> facilities;
    private List<BookingDTO> bookingList;
    private String image;

    public ProvinceDto getProvince() {
        return province;
    }

    public void setProvince(ProvinceDto province) {
        this.province = province;
    }

    public List<BookingDTO> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<BookingDTO> bookingList) {
        this.bookingList = bookingList;
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

    public List<FacilitiesDTO> getFacilities() {
        return facilities;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
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


    public void setFacilities(List<FacilitiesDTO> facilities) {
        this.facilities = facilities;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
