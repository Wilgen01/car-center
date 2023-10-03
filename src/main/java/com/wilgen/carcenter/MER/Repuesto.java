package com.wilgen.carcenter.MER;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Repuesto {
    @Id
    private Long id;
    @Column(length = 100)
    private String nombre;
    private  Integer precio_unitario;
    @ManyToMany(mappedBy = "repuestos")
    private Set<Servicio> servicios = new HashSet<>();
}
