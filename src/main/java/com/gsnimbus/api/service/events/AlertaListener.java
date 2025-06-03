package com.gsnimbus.api.service.events;

import com.gsnimbus.api.service.AlertaBairroService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlertaListener {
    private final AlertaBairroService alertaBairroService;

    @EventListener
    public void aoSalvarAlerta(AlertaCriadoEvent event){
        alertaBairroService.save(event.getIdBairro(), event.getIdAlerta());
    }

}
