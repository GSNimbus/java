package com.gsnimbus.api.service.events;

import com.gsnimbus.api.service.AlertaAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrevisaoListener {
    private final AlertaAIService alertaAIService;

    @EventListener
    public void aoSalvarPrevisao(PrevisaoCriadaEvent event){
        alertaAIService.processarAlerta(event.getPrevisao(), event.getIdBairro());
    }
}
