package com.gsnimbus.api.service.events;

import com.gsnimbus.api.service.PrevisaoApiService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BairroListener {
    private final PrevisaoApiService previsaoApiService;

    @EventListener
    public void aoSalvarBairro(BairroCriadoEvent event) {
        previsaoApiService.savePrevisao(event.getBairro());
    }
}
