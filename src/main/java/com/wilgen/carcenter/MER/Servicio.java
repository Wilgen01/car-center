package com.wilgen.carcenter.MER;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Servicio {
    @Id
    private Long id;
    private Date fecha;
    private Integer limite_presupuesto;
    private Integer mano_obra;
    private Integer horas_estimadas;
    @ManyToOne
    @JoinColumn(name = "mecanico_id")
    private Mecanico mecanico;
    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;
    @OneToMany(mappedBy = "servicio")
    private Set<Foto> fotos = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "servicio_repuesto",
            joinColumns = @JoinColumn(name = "servicio_id"),
            inverseJoinColumns = @JoinColumn(name = "repuesto_id"))
    private Set<Repuesto> repuestos = new HashSet<>();

}
