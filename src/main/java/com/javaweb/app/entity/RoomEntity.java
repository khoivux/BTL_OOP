package com.javaweb.app.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
public class RoomEntity {
    @Id
    private Long id;

    @Column(name = "type")
    private String type;


    @Column(name = "numbers")
    private Long numbers;

    @ManyToMany(mappedBy = "rooms")
    private List<HomestayEntity> homestays;

    public RoomEntity(Long id, String type, Long numbers) {
        this.id = id;
        this.type = type;
        this.numbers = numbers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getNumbers() {
        return numbers;
    }

    public void setNumbers(Long numbers) {
        this.numbers = numbers;
    }

}