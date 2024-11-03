package com.javaweb.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HomestaySearchRequest {
    private String name;
    private String address;
    private Long priceFrom;
    private Long priceTo;
    private Long provinceId;
    private Long rating;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private List<Long> roomFacilities = new ArrayList<>();
    private List<Long> homestayFacilities = new ArrayList<>();

    public HomestaySearchRequest() {
    }

    public HomestaySearchRequest(Builder builder) {
        this.name = builder.name;
        this.address = builder.address;
        this.priceFrom = builder.priceFrom;
        this.priceTo = builder.priceTo;
        this.provinceId = builder.provinceId;
        this.rating = builder.rating;
        this.checkInDate = builder.checkInDate;
        this.checkOutDate = builder.checkOutDate;
        this.homestayFacilities = builder.homestayFacilities;
        this.roomFacilities = builder.roomFacilities;
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

    public List<Long> getRoomFacilities() {
        return roomFacilities;
    }

    public List<Long> getHomestayFacilities() {
        return homestayFacilities;
    }

    public static class Builder {
        private String name;
        private String address;
        private Long priceFrom;
        private Long priceTo;
        private Long provinceId;
        private Long rating;
        private LocalDate checkInDate;
        private LocalDate checkOutDate;
        private List<Long> roomFacilities = new ArrayList<>();
        private List<Long> homestayFacilities = new ArrayList<>();

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
        public Builder setRoomFacilities(List<Long> roomFacilities) {
            this.roomFacilities = roomFacilities;
            return this;
        }
        public Builder setHomestayFacilities(List<Long> homestayFacilities) {
            this.homestayFacilities = homestayFacilities;
            return this;
        }
        public HomestaySearchRequest build() {
            return new HomestaySearchRequest(this);
        }
    }
}
