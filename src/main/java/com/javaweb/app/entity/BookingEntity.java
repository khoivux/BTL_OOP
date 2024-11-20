package com.javaweb.app.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "booking")
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "checkin_date")
    private LocalDate checkInDate;

    @Column(name = "checkout_date")
    private LocalDate checkOutDate;

    @Column(name = "booking_time")
    private LocalDateTime bookingTime;

    @Column(name = "status")
    private String status;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_Email")
    private String customerEmail;

    @Column(name = "customer_Phone")
    private String customerPhone;

    @ManyToOne
    @JoinColumn(name = "homestay_id")
    private  HomestayEntity homestay;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "booking_service", // Tên bảng trung gian
            joinColumns = @JoinColumn(name = "booking_id"), // Khóa ngoại trỏ tới BookingEntity
            inverseJoinColumns = @JoinColumn(name = "service_id") // Khóa ngoại trỏ tới ServiceEntity
    )
    private List<ServiceEntity> services;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setHomestay(HomestayEntity homestay) {
        this.homestay = homestay;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setServices(List<ServiceEntity> services) {
        this.services = services;
    }
}
