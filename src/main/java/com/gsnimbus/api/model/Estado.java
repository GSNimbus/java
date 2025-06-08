package com.gsnimbus.api.model;


import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
@Entity
@Table(name = "t_nimbus_estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Long idEstado;
    @NotEmpty(message = "Valor inválido para o estado")
    @Length(max = 100, message = "Valor inválido para o estado. O valor deve ter no máximo 100 caracteres")
    @Column(name = "nm_estado")
    private String nmEstado;
    @ManyToOne
    @JoinColumn(name = "id_pais")
    private Pais idPais;
}
