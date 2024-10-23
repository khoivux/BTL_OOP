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

    public HomestayDto() {
    }

    public HomestayDto(Long id, String name, Long price, String address, Long provinceid, Long rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.address = address;
        this.provinceid = provinceid;
        this.rating = rating;
    }
}
