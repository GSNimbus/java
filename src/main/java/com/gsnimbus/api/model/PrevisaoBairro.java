package com.gsnimbus.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_nimbus_previsao_bairro")
public class PrevisaoBairro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_previsao")
    private Previsao previsao;
    @ManyToOne
    @JoinColumn(name = "id_bairro")
    private Bairro bairro;

}
