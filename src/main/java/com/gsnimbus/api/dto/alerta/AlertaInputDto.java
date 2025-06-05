package com.gsnimbus.api.dto.alerta;

import com.gsnimbus.api.model.Bairro;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AlertaInputDto {
    private String time;
    private BigDecimal temperature2M;
    private BigDecimal precipitation;
    private Long rain;
    private Long weatherCode;
    private Double windSpeed10M;
    private Double windGusts10M;
    private Long relativeHumidity2M;
    private Double apparentTemperature;
    private Double surfacePressure;
    private Bairro idBairro;
}
