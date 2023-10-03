package com.wilgen.carcenter.MER;

import jakarta.persistence.*;

@Entity
public class Mecanico {
    @Id
    private Integer documento;
    @Column(length = 50)
    private String nombre;
    @Column(length = 50)
    private String apellido;
    @Column(length = 100)
    private String correo;
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;
}
