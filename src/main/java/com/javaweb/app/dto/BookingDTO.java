package com.javaweb.app.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingDTO {
    private Long homestay_id, user_id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDateTime bookingTime;
    private String status;

    public void setHomestay_id(Long homestay_id) {
        this.homestay_id = homestay_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getHomestay_id() {
        return homestay_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public String getStatus() {
        return status;
    }
}
