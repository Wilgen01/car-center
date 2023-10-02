package com.wilgen.carcenter.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Vehicle {

    @Id
    @Column(length = 6)
    private String plate;
    @Column(length = 50)
    private String color;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
}
