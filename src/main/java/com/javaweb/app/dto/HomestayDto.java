package com.javaweb.app.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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
}
