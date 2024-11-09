package com.javaweb.app.entity;

import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "homestay")
public class HomestayEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "rating", nullable = false)
    private Long rating;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "numberOfRooms")
    private Long numberOfRooms;

    @Column(name = "capacity")
    private Long capacity;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private ProvinceEntity province;

    @OneToMany(mappedBy = "homestay",  cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BookingEntity> bookingList = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "homestay_service", // Tên bảng trung gian
            joinColumns = @JoinColumn(name = "homestay_id"), // Khóa ngoại trỏ tới HomestayEntity
            inverseJoinColumns = @JoinColumn(name = "service_id") // Khóa ngoại trỏ tới ServiceEntity
    )
    private List<ServiceEntity> services;

    @ManyToMany
    @JoinTable(
            name = "homestay_facilities", // Tên bảng trung gian
            joinColumns = @JoinColumn(name = "homestay_id"), // Khóa ngoại trỏ tới HomestayEntity
            inverseJoinColumns = @JoinColumn(name = "facilities_id") // Khóa ngoại trỏ tới ServiceEntity
    )
    private List<FacilitiesEntity> facilities;

    @ManyToMany
    @JoinTable(
            name = "homestay_room", // Tên bảng trung gian
            joinColumns = @JoinColumn(name = "homestay_id"), // Khóa ngoại trỏ tới HomestayEntity
            inverseJoinColumns = @JoinColumn(name = "room_id") // Khóa ngoại trỏ tới ServiceEntity
    )
    private List<RoomEntity> rooms;

    public HomestayEntity() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setNumberOfRooms(Long numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public ProvinceEntity getProvince() {
        return province;
    }

    public void setProvince(ProvinceEntity province) {
        this.province = province;
    }


    public List<FacilitiesEntity> getFacilities() {
        return facilities;
    }

    public List<RoomEntity> getRooms() {
        return rooms;
    }
}