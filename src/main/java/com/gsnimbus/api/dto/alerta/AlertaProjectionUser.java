package com.gsnimbus.api.dto.alerta;

import com.gsnimbus.api.model.Alerta;
import lombok.Data;

@Data
public class AlertaProjectionUser {
    private String nomeGrupoLocacalizacao;
    private Alerta alerta;
}
