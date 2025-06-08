package com.gsnimbus.api.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
@Table(name = "t_nimbus_previsao")
public class Previsao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_previsao")
    private Long id;
    @Column(name = "nm_hora")
    private String time;
    @Column(name = "nr_temperatura")
    private BigDecimal temperature2M;

    @Transient
    @Column(name = "nr_precipitacao")
    private BigDecimal precipitation;
    @Column(name = "nr_chuva")
    private Long rain;

    @Column(name = "nr_codigo_chuva")
    private Long weatherCode;
    @Column(name = "nr_velocidade_vento")
    private Double windSpeed10M;
    @Column(name = "nr_rajada_vento")
    private Double windGusts10M;
    @Column(name = "nr_umidade")
    private Long relativeHumidity2M;

    @Transient
    @Column(name = "nr_temperatura_aparente")
    private Double apparentTemperature;

    @Transient
    @Column(name = "nr_pressao_superficie")
    private Double surfacePressure;

    @ManyToOne
    @JoinColumn(name = "id_bairro")
    private Bairro idBairro;
}
