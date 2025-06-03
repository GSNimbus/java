package com.gsnimbus.api.service.events;

import com.gsnimbus.api.model.Alerta;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AlertaCriadoEvent {
    private final Long idAlerta;
    private final Long idBairro;
}
