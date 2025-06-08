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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data
@Entity
@Table(name = "t_nimbus_endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long idEndereco;

    @NotEmpty(message = "Valor inválido para nome do bairro")
    @Length(min = 8, max = 8,message = "Valor inválido para nome do bairro. O valor deve ter no mínimo 8 caracteres")
    @Column(name = "nr_cep")
    private String cep;

    @NotBlank (message = "Valor inválido para nome do logradouro")
    @Size(max = 200, message = "Valor inválido para nome do logradouro. O valor deve ter no máximo 200 caracteres")
    @Column(name = "nm_logradouro")
    private String nmLogradouro;

    @NotNull(message = "Valor inválido para número do logradouro")
    @Max(value = 99999, message = "Valor inválido para número do logradouro. O valor deve ter no máximo 5 caracteres")
    @Column(name = "nr_logradouro")
    private Integer nrLogradouro;

    @ManyToOne
    @JoinColumn(name = "id_bairro")
    private Bairro idBairro;
}
