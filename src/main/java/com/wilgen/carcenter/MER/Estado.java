package com.wilgen.carcenter.MER;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Estado {
    @Id
    private Long id;
    @Column(length = 50)
    private String nombre;
}
