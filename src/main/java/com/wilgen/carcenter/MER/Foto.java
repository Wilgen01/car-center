package com.wilgen.carcenter.MER;

import jakarta.persistence.*;

@Entity
public class Foto {
    @Id
    private Integer id;
    private String url;
    @Column(length = 10)
    private String formato;
    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;
}
