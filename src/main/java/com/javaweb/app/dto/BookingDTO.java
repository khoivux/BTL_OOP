package com.javaweb.app.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingDTO {
    private Long homestay_id, user_id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String status;

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
