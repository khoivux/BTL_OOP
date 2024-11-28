package com.javaweb.app.dto;


import com.javaweb.app.entity.BookingEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingDTO {
    private Long id;
    private String userPhone;
    private String userEmail;
    private String homestayName;
    private Long homestayPrice;
    private String userFullName;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDateTime bookingTime;
    private String status;
    private Long homestay_id, user_id;
    public BookingDTO() {
    }
    public BookingDTO(Long id,String userPhone,String UserEmail,String homestayName,Long homestayPrice, String userFullName, LocalDate checkInDate, LocalDate checkOutDate, LocalDateTime bookingTime, String status) {
        this.id = id;
        this.userPhone = userPhone;
        this.userEmail = UserEmail;
        this.homestayName = homestayName;
        this.homestayPrice = homestayPrice;
        this.userFullName = userFullName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingTime = bookingTime;
        this.status = status;
    }

    public BookingDTO(BookingEntity booking) {
    }
// Getters và Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {return userEmail;}

    public void setUserEmail(String userEmail) {this.userEmail = userEmail;}

    public String getUserPhone() {return userPhone;}
    public void setUserPhone(String userPhone) {this.userPhone = userPhone;}

    public void setHomestayName(String homestayName) {
        this.homestayName = homestayName;
    }

    public Long getHomestayPrice() {
        return homestayPrice;
    }

    public void setHomestayPrice(Long homestayPrice) {
        this.homestayPrice = homestayPrice;
    }


    public void setUserName(String userFullName) {
        this.userFullName = userFullName;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getHomestay_id() {
        return homestay_id;
    }

    public void setHomestay_id(Long homestay_id) {
        this.homestay_id = homestay_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }


    public String getHomestayName() {
        return homestayName;
    }

    public String getUserFullName() {
        return userFullName;
    }
    public void setUserFullName(String userFullName) {this.userFullName = userFullName;}
}
