package com.sistemas.basico.dominio;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trabajo_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_trabajo_tarea"))
    private Trabajo trabajo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etapa_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_etapa_tarea"))
    private Etapa etapa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_empleado_tarea"))
    private Empleado empleado;


    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaFin;
}
