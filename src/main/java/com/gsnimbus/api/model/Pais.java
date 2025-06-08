package com.gsnimbus.api.model;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
@Entity
@Table(name = "t_nimbus_pais")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pais")
    private Long idPais;
    @NotEmpty(message = "Valor inválido para o país")
    @Length(max = 100, message = "Valor inválido para o país. O valor deve ter no máximo 100 caracteres")
    @Column(name = "nm_pais")
    private String nmPais;
}
