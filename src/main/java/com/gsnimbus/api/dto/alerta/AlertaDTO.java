package com.gsnimbus.api.dto.alerta;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gsnimbus.api.model.TipoAlerta;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AlertaDTO {
    String risco;
    TipoAlerta tipo;
    String mensagem;
    @JsonIgnore
    Long idBairro;
}
