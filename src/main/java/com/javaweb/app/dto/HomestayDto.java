package com.javaweb.app.dto;

public class HomestayDto {
    private Long id;
    private String name;
    private Long price;
    private String address;
    private Long provinceid;
    private Long rating;
    private String description;

    public HomestayDto() {
    }

    public HomestayDto(Long id, String name, Long price, Long rating, String description, String address, Long provinceid) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.address = address;
        this.provinceid = provinceid;
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
}
