package com.javaweb.app.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "booking")
public class BookingEntity {
    @Id
    private Long id;

    @Column(name = "checkin_date")
    private LocalDate checkInDate;

    @Column(name = "checkout_date")
    private LocalDate checkOutDate;

    @Column(name = "booking_time")
    private LocalDateTime bookingTime;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "homestay_id")
    private  HomestayEntity homestay;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private  RoomEntity room;

    @ManyToMany
    @JoinTable(
            name = "booking_service", // Tên bảng trung gian
            joinColumns = @JoinColumn(name = "booking_id"), // Khóa ngoại trỏ tới BookingEntity
            inverseJoinColumns = @JoinColumn(name = "service_id") // Khóa ngoại trỏ tới ServiceEntity
    )
    private List<ServiceEntity> services;



    public BookingEntity() {
    }

    public BookingEntity(Long id, LocalDate checkInDate, LocalDate checkOutDate, LocalDateTime bookingTime, String status, HomestayEntity homestay, RoomEntity room, List<ServiceEntity> services) {
        this.id = id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingTime = bookingTime;
        this.status = status;
        this.homestay = homestay;
        this.room = room;
        this.services = services;
    }
}
