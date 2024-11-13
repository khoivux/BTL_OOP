package com.javaweb.app.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "username")
    private String username;

    @Column (name = "email")
    private String email;

    @Column (name = "password")
    private String password;

    @Column (name = "role")
    private String role;

    public User() {
        this.username = "";
        this.email = "";
        this.password = "";
        this.role = "user";
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = "user";
    }

    public String getUserName() {
        return username;
    }

    public String getUserEmail() {
        return email;
    }

    public String getUserPassword() {
        return password;
    }

    public String getUserRole() {
        return role;
    }

    private String Otp;
    private LocalDateTime Expired;

    public void setOtp(String otp) {
        this.Otp = otp;
    }
    public Object getOtp() {
        return Otp;
    }
    public void setOtpExpiry(LocalDateTime localDateTime) {
        this.Expired = localDateTime;
    }
    public LocalDateTime getOtpExpiry() {
        return Expired;
    }
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}
