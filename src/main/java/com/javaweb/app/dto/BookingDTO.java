package com.javaweb.app.dto;

import com.javaweb.app.entity.HomestayEntity;
import com.javaweb.app.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingDTO {
    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDateTime bookingTime;
    private String status;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private HomestayEntity homestay;
    private Long stayDuration;
    private Long total;
    private User user;
    private Long checkInTime;
    public Long getId() {
        return id;
    }

    public Long getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Long checkInTime) {
        this.checkInTime = checkInTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getStatus() {
        return status;
    }

    public Long getStayDuration() {
        return stayDuration;
    }

    public void setStayDuration(Long stayDuration) {
        this.stayDuration = stayDuration;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public HomestayEntity getHomestay() {
        return homestay;
    }

    public void setHomestay(HomestayEntity homestay) {
        this.homestay = homestay;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
