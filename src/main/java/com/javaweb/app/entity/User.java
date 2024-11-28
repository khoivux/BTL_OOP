package com.javaweb.app.entity;

import jakarta.persistence.*;

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

    public User() {
        this.username = "";
        this.fullName = "";
        this.email = "";
        this.password = "";
        this.phoneNumber = "";
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

    public String getFullName() {
        return fullName;
    }
    public Long getUserID(){return id; }
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

    public String getUserPhone() { return phoneNumber; }
}
