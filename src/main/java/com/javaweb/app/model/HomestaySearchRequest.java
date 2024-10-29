package com.javaweb.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HomestaySearchRequest {
    private String name;
    private String address;
    private Long priceFrom;
    private Long priceTo;
    private Long provinceId;
    private Long rating;
    public HomestaySearchRequest(Builder builder) {
        this.name = builder.name;
        this.address = builder.address;
        this.priceFrom = builder.priceFrom;
        this.priceTo = builder.priceTo;
        this.provinceId = builder.provinceId;
        this.rating = builder.rating;
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

    public Long getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Long priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Long getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Long priceTo) {
        this.priceTo = priceTo;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public static class Builder {
        private String name;
        private String address;
        private Long priceFrom;
        private Long priceTo;
        private Long provinceId;
        private Long rating;

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

        public HomestaySearchRequest build() {
            return new HomestaySearchRequest(this);
        }
    }
}
