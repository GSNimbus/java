package com.gsnimbus.api.dto.previsao.api;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonIgnoreProperties(ignoreUnknown = true) 
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CurrentDto(
        String time,
        @JsonProperty("temperature_2m") BigDecimal temperature2M,
        BigDecimal precipitation,
        Long rain,
        @JsonProperty("weather_code") Long weatherCode,
        @JsonProperty("wind_speed_10m") Double windSpeed10M,
        @JsonProperty("wind_gusts_10m") Double windGusts10M,
        @JsonProperty("relative_humidity_2m") Long relativeHumidity2M,
        @JsonProperty("apparent_temperature") Double apparentTemperature,
        @JsonProperty("surface_pressure") Double surfacePressure
) {
}
