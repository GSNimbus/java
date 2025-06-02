package com.gsnimbus.api.dto.previsao.api;

import java.math.BigDecimal;

public record CurrentDto(
        String time,
        BigDecimal temperature2M,
        BigDecimal precipitation,
        Long rain,
        Long weatherCode,
        Double windSpeed10M,
        Double windGusts10M,
        Long relativeHumidity2M
) {
}
