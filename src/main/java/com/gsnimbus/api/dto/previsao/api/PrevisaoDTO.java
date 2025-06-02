package com.gsnimbus.api.dto.previsao.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PrevisaoDTO(
        CurrentDto currentDto
) {
}
