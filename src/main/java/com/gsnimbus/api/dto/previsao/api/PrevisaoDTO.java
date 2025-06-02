package com.gsnimbus.api.dto.previsao.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PrevisaoDTO(
        @JsonProperty("current") CurrentDto currentDto) {
}
