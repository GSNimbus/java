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
@Table(name = "t_nimbus_cidade")
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cidade")
    private Long idCidade;
    @NotEmpty(message = "Valor inválido para nome da cidade")
    @Length(max = 200, message = "Valor inválido para nome da cidade. O valor deve ter no máximo 200 caracteres")
    @Column(name = "nm_cidade")
    private String nmCidade;
    @ManyToOne
    @JoinColumn(name = "id_estado")
    private Estado idEstado;

}
