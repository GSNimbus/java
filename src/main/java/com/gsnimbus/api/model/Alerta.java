package com.gsnimbus.api.model;

import java.time.LocalDateTime;
import java.time.ZoneId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "t_nimbus_alerta")
public class Alerta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alerta")
    private Long id;
    @Column(name = "ds_risco")
    private String risco;
    @Enumerated(EnumType.STRING)
    @Column(name = "ds_tipo")
    private TipoAlerta tipo;
    @Column(name = "ds_mensagem")
    private String mensagem;

    @Column(name = "horario_alerta")
    private LocalDateTime horarioAlerta;

    @ManyToOne
    @JoinColumn(name = "id_bairro")
    private Bairro idBairro;

    @PrePersist
    protected void onCreate() {
        this.horarioAlerta = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }
}
