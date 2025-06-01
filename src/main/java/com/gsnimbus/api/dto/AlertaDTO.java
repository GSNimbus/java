package com.gsnimbus.api.dto;

import java.util.Date;

import com.gsnimbus.api.model.TipoAlerta;

public record AlertaDTO(
    String risco,
    TipoAlerta tipo,
    String mensagem,
    Date horarioAlerta,
    Long idLocalizacao
) {}
