package com.javaweb.app.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HomestaySearchRequestDTO {
    private Long id;
    private String name;
    private String address;
    private Long priceFrom;
    private Long priceTo;
    private Long provinceId;
    private Long rating;
    private Long capacity;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private List<Long> facilities = new ArrayList<>();
    private List<Long> rooms = new ArrayList<>();
    private List<Long> services = new ArrayList<>();
    public HomestaySearchRequestDTO() {
    }

    public HomestaySearchRequestDTO(Builder builder) {
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

    public Long getCapacity() {
        return capacity;
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
        private Long capacity;
        private LocalDate checkInDate;
        private LocalDate checkOutDate;
        private List<Long> facilities = new ArrayList<>();
        private List<Long> rooms = new ArrayList<>();
        private List<Long> services = new ArrayList<>();

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder priceFrom(Long priceFrom) {
            this.priceFrom = priceFrom;
            return this;
        }

        public Builder priceTo(Long priceTo) {
            this.priceTo = priceTo;
            return this;
        }

        public Builder provinceId(Long provinceId) {
            this.provinceId = provinceId;
            return this;
        }

        public Builder rating(Long rating) {
            this.rating = rating;
            return this;
        }

        public Builder checkInDate(LocalDate checkInDate) {
            this.checkInDate = checkInDate;
            return this;
        }

        public Builder checkOutDate(LocalDate checkOutDate) {
            this.checkOutDate = checkOutDate;
            return this;
        }
        public Builder facilities(List<Long> facilities) {
            this.facilities = facilities;
            return this;
        }
        public Builder rooms(List<Long> rooms) {
            this.rooms = rooms;
            return this;
        }
        public Builder services(List<Long> services) {
            this.services = services;
            return this;
        }
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        public Builder capacity(Long capacity) {
            this.capacity = capacity;
            return this;
        }
        public HomestaySearchRequestDTO build() {
            return new HomestaySearchRequestDTO(this);
        }
    }
}
