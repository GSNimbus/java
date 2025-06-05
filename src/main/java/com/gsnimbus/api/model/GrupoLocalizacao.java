package com.gsnimbus.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_nimbus_gp_localizacao")
public class GrupoLocalizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nm_grupo")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
