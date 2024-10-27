package com.javaweb.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "homestay")
public class HomestayEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "rating", nullable = false)
    private Long rating;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "provinceid")
    private ProvinceEntity province;


    public HomestayEntity(Long id, String name, Long price, String address, Long rating, String description, ProvinceEntity province) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.address = address;
        this.rating = rating;
        this.description = description;
        this.province = province;
    }
}
