package com.gsnimbus.api.dto.alerta; // Pacote atualizado

import java.util.Date;

import com.gsnimbus.api.model.TipoAlerta;

public record AlertaDTO(
    String risco,
    TipoAlerta tipo,
    String mensagem,
    Long idLocalizacao
) {}
