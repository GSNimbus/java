package com.gsnimbus.api.dto.previsao.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PrevisaoDTO {
    @JsonProperty("current")
    CurrentDto currentDto;
    @JsonIgnore
    Long idBairro;
}