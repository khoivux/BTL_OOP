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

    @Column (name = "fullName")
    private String fullName;

    @Column (name = "email")
    private String email;

    @Column (name = "password")
    private String password;

    @Column (name = "role")
    private String role;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    // now we add address and date
    @Column(name = "address")
    private String address;

    @Column(name = "date", columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private LocalDateTime date;

    public User() {
        this.username = "";
        this.fullName = "";
        this.email = "";
        this.password = "";
        this.phoneNumber = "";
        this.address = "";
        this.role = "user";
    }

    public User(String username, String fullName, String email, String password, String phoneNumber) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = "user";
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }


    public void setFullName(String newFullName) {
        this.fullName = newFullName;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public void setPhoneNumber(String newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }

    public void setAddress(String newAddress) {
        this.address = newAddress;
    }
}
