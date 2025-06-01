package com.gsnimbus.api.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Table(name = "t_nimbus_cidade")
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCidade;
    @NotEmpty(message = "Valor inválido para nome da cidade")
    @Length(max = 200, message = "Valor inválido para nome da cidade. O valor deve ter no máximo 200 caracteres")
    private String nmCidade;
    @ManyToOne
    @JoinColumn(name = "id_estado")
    private Estado idEstado;

}
