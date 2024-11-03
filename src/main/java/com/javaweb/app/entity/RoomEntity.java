package com.javaweb.app.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
public class RoomEntity {
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private Long price;

    @ManyToOne
    @JoinColumn(name = "homestay_id", nullable = false)
    private HomestayEntity homestay;

    @OneToMany(mappedBy = "room",  cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BookingEntity> bookingList = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "room_facilities", // Tên bảng trung gian
            joinColumns = @JoinColumn(name = "room_id"), // Khóa ngoại trỏ tới HomestayEntity
            inverseJoinColumns = @JoinColumn(name = "facilities_id") // Khóa ngoại trỏ tới ServiceEntity
    )
    private List<RoomFacilitiesEntity> facilities;

    public RoomEntity() {
    }

    public RoomEntity(Long id, String name, String type, Long price, HomestayEntity homestay) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.homestay = homestay;
    }
}
