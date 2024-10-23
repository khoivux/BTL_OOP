package com.javaweb.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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
}
