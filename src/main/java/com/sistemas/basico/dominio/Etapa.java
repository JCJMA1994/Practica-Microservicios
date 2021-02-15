package com.sistemas.basico.dominio;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Etapa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    @NotBlank(message = "La etapa no debe estar en blanco")
    private String nombre;
}
