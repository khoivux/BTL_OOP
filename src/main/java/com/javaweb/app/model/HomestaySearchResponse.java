package com.javaweb.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class HomestaySearchResponse {
    private Long id;
    private String name;
    private String address;
    private Long price;
    private String province;
    private Long rating;

    public HomestaySearchResponse() {
    }

    public HomestaySearchResponse(Long id, String name, String address, String province, Long price, Long rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.province = province;
        this.price = price;
        this.rating = rating;
    }
}
