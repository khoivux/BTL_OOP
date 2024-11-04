package com.javaweb.app.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "province")
public class ProvinceEntity {
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "province", fetch = FetchType.LAZY)
    private List<HomestayEntity> homeStays = new ArrayList<>();

    public ProvinceEntity() {
    }

    public ProvinceEntity(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<HomestayEntity> getHomeStays() {
        return homeStays;
    }

    public void setHomeStays(List<HomestayEntity> homeStays) {
        this.homeStays = homeStays;
    }
}
