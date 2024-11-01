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

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "rating", nullable = false)
    private Long rating;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private ProvinceEntity province;

    @OneToMany(mappedBy = "homestay", fetch = FetchType.LAZY)
    private List<BookingEntity> bookingList = new ArrayList<>();

    public HomestayEntity() {
    }

    public HomestayEntity(Long id, String name, Long price, String address, Long rating, String description, ProvinceEntity province, List<BookingEntity> bookingList) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.address = address;
        this.rating = rating;
        this.description = description;
        this.province = province;
        this.bookingList = bookingList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProvince(ProvinceEntity province) {
        this.province = province;
    }

    public void setBookingList(List<BookingEntity> bookingList) {
        this.bookingList = bookingList;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }

    public Long getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public ProvinceEntity getProvince() {
        return province;
    }

    public List<BookingEntity> getBookingList() {
        return bookingList;
    }
}