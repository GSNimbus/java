package com.gsnimbus.api.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;



@Data
@Entity
@Table(name = "t_gpMottu_endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEndereco;

    @NotEmpty(message = "Valor inválido para nome do bairro")
    @Length(min = 8, max = 8,message = "Valor inválido para nome do bairro. O valor deve ter no mínimo 8 caracteres")
    @Column(unique = true)
    private String cep;

    @NotBlank (message = "Valor inválido para nome do logradouro")
    @Size(max = 200, message = "Valor inválido para nome do logradouro. O valor deve ter no máximo 200 caracteres")
    private String nmLogradouro;

    @NotNull(message = "Valor inválido para número do logradouro")
    @Max(value = 99999, message = "Valor inválido para número do logradouro. O valor deve ter no máximo 5 caracteres")
    private Integer nrLogradouro;

    @ManyToOne
    @JoinColumn(name = "id_cidade")
    private Cidade idCidade;
}
