package com.gsnimbus.api.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data
@Entity
@Table(name = "t_gpMottu_estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstado;
    @NotEmpty(message = "Valor inválido para o estado")
    @Length(max = 100, message = "Valor inválido para o estado. O valor deve ter no máximo 100 caracteres")
    private String nmEstado;
    @ManyToOne
    @JoinColumn(name = "id_pais")
    private Pais idPais;
}
