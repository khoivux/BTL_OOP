package com.javaweb.app.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HomestayRequestDTO {
    private Long id;
    private String name;
    private String address;
    private Long priceFrom;
    private Long priceTo;
    private Long provinceId;
    private Long rating;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private List<Long> facilities = new ArrayList<>();
    private List<Long> rooms = new ArrayList<>();
    private List<Long> services = new ArrayList<>();
    public HomestayRequestDTO() {
    }

    public HomestayRequestDTO(Builder builder) {
        this.name = builder.name;
        this.address = builder.address;
        this.priceFrom = builder.priceFrom;
        this.priceTo = builder.priceTo;
        this.provinceId = builder.provinceId;
        this.rating = builder.rating;
        this.checkInDate = builder.checkInDate;
        this.checkOutDate = builder.checkOutDate;
        this.facilities = builder.facilities;
        this.id = builder.id;
        this.rooms = builder.rooms;
        this.services = builder.services;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Long getPriceFrom() {
        return priceFrom;
    }

    public Long getPriceTo() {
        return priceTo;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public Long getRating() {
        return rating;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public List<Long> getHomestayFacilities() {
        return facilities;
    }
    public Long getId() {
        return id;
    }

    public List<Long> getFacilities() {
        return facilities;
    }

    public List<Long> getRooms() {
        return rooms;
    }

    public List<Long> getServices() {
        return services;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String address;
        private Long priceFrom;
        private Long priceTo;
        private Long provinceId;
        private Long rating;
        private LocalDate checkInDate;
        private LocalDate checkOutDate;
        private List<Long> facilities = new ArrayList<>();
        private List<Long> rooms = new ArrayList<>();
        private List<Long> services = new ArrayList<>();

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setPriceFrom(Long priceFrom) {
            this.priceFrom = priceFrom;
            return this;
        }

        public Builder setPriceTo(Long priceTo) {
            this.priceTo = priceTo;
            return this;
        }

        public Builder setProvinceId(Long provinceId) {
            this.provinceId = provinceId;
            return this;
        }

        public Builder setRating(Long rating) {
            this.rating = rating;
            return this;
        }

        public Builder setCheckInDate(LocalDate checkInDate) {
            this.checkInDate = checkInDate;
            return this;
        }

        public Builder setCheckOutDate(LocalDate checkOutDate) {
            this.checkOutDate = checkOutDate;
            return this;
        }
        public Builder setFacilities(List<Long> facilities) {
            this.facilities = facilities;
            return this;
        }
        public Builder setRooms(List<Long> rooms) {
            this.rooms = rooms;
            return this;
        }
        public Builder setServices(List<Long> services) {
            this.services = services;
            return this;
        }
        public Builder setId(Long id) {
            this.id = id;
            return this;
        }
        public HomestayRequestDTO build() {
            return new HomestayRequestDTO(this);
        }
    }
}
