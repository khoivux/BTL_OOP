package com.javaweb.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Column(name = "phoneNumber")
    private String phoneNumber;

    public User() {
        this.username = "";
        this.email = "";
        this.password = "";
        this.phoneNumber = "";
        this.role = "user";
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = "user";
    }

    public User(String username, String email, String password, String phoneNumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = "user";
        this.phoneNumber = phoneNumber;
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
}
