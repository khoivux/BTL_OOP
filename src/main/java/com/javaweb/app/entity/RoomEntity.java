package com.javaweb.app.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "numbers")
    private Long numbers;

    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "image", columnDefinition = "MEDIUMBLOB")  // Chỉ định cột và các thuộc tính của nó
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "homestay_id")
    private HomestayEntity homestay;

    public RoomEntity() {
    }

    public RoomEntity(String type, Long numbers, String description) {
        this.type = type;
        this.numbers = numbers;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HomestayEntity getHomestay() {
        return homestay;
    }

    public void setHomestay(HomestayEntity homestay) {
        this.homestay = homestay;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
