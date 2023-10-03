package com.wilgen.carcenter.MER;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Factura {
    @Id
    @Column(length = 20)
    private String consecutivo;
    @OneToOne
    private Servicio servicio;
    private Integer impuesto;
    private Integer valor;
    private Integer valor_total;


}
