package com.gsnimbus.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_nimbus_bairro")
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bairro")
    private Long id;

    @Column(name = "nm_bairro")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_cidade")
    private Cidade idCidade;

    @ManyToOne
    @JoinColumn(name = "id_localizacao_id")
    private Localizacao idLocalizacao;

}
