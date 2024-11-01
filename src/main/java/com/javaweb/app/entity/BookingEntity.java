package com.javaweb.app.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    public BookingEntity() {
    }

    public BookingEntity(Long id, LocalDate checkInDate, LocalDate checkOutDate, LocalDateTime bookingTime, String status, HomestayEntity homestay) {
        this.id = id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingTime = bookingTime;
        this.status = status;
        this.homestay = homestay;
    }
}
