package com.javaweb.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table (name = "users")
public class User {
    @Id
    private Long id;

    @Column (name = "email")
    private String email;

    @Column (name = "password")
    private String password;

    @Column (name = "role")
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BookingEntity> bookings;

    public User() {
        this.email = "";
        this.password = "";
        this.role = "user";
    }

    public User(String username, String password) {
        this.email = username;
        this.password = password;
        this.role = "user";
    }

    public String getUsername() {
        return email;
    }

    public String getUserPassword() {
        return password;
    }

    public String getUserRole() {
        return role;
    }
}
