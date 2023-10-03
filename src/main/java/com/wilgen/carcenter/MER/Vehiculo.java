package com.wilgen.carcenter.MER;

import jakarta.persistence.*;

import java.time.Year;

@Entity
public class Vehiculo {
    @Id
    @Column(length = 6)
    private String placa;
    @Column(length = 20)
    private String color;
    private Year modelo;
    @ManyToOne
    @JoinColumn(name = "marca_id")
    private  Marca marca;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
