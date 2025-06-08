package com.gsnimbus.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    @JoinColumn(name = "id_localizacao")
    private Localizacao idLocalizacao;

}
