package com.javaweb.app.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "service")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @ManyToMany(mappedBy = "services")
    private List<BookingEntity> bookings;

    @ManyToMany(mappedBy = "services")
    private List<HomestayEntity> homestays;
}
