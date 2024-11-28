package com.javaweb.app.dto;

import com.javaweb.app.entity.HomestayEntity;

import java.util.List;

public class ProvinceDto {
    private Long id;
    private String name;

    public ProvinceDto() {
    }

    public ProvinceDto(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
