package com.gsnimbus.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "t_nimbus_previsao")
public class Previsao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nm_hora")
    private String time;
    @Column(name = "nr_temperatura")
    private BigDecimal temperature2M;
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


}
