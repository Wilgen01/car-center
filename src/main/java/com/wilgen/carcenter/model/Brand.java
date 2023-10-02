package com.wilgen.carcenter.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String name;
}
