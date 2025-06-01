package com.gsnimbus.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "t_nimbus_alerta")
public class Alerta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String risco;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_alerta")
    private TipoAlerta tipo;
    private String mensagem;
    @Temporal(TemporalType.TIMESTAMP)
    private Date horarioAlerta;
    @ManyToOne
    @JoinColumn(name = "id_localizacao")
    private Localizacao idLocalizacao;

    @PrePersist
    protected void onCreate() {
        this.horarioAlerta = new Date();
    }
}
