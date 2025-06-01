package com.gsnimbus.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


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
    private String nmPais;
}
