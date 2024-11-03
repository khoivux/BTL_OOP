package com.javaweb.app.entity;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "roomfacilities")
public class RoomFacilitiesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "facilities")
    private ArrayList<RoomEntity> rooms;

    public RoomFacilitiesEntity() {
    }

    public RoomFacilitiesEntity(Long id, String name, ArrayList<RoomEntity> rooms) {
        this.id = id;
        this.name = name;
        this.rooms = rooms;
    }
}
