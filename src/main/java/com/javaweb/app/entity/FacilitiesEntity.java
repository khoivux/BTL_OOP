package com.javaweb.app.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "homestayfacilities")
public class FacilitiesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "facilities")
    private List<HomestayEntity> homestays;

    public FacilitiesEntity() {
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
