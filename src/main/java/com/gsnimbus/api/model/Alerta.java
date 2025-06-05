package com.gsnimbus.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;

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
