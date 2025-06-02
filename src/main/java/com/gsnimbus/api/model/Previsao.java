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
    private String time;
    private BigDecimal temperature2M;
    private BigDecimal precipitation;
    private Long rain;
    private Long weatherCode;
    private Double windSpeed10M;
    private Double windGusts10M;
    private Long relativeHumidity2M;


}
