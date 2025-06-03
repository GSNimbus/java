package com.gsnimbus.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_nimbus_alerta_bairro")
public class AlertaBairro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_alerta")
    private Alerta alerta;

    @ManyToOne
    @JoinColumn(name = "id_bairro")
    private Bairro bairro;


}
