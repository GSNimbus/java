package com.gsnimbus.api.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gsnimbus.api.service.BairroService;
import com.gsnimbus.api.service.PrevisaoApiService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@RequiredArgsConstructor
@Log4j2
public class PrevisaoScheduler {

    private final BairroService bairroService;
    private final PrevisaoApiService previsaoApiService;

    @Scheduled(fixedRate = 60 * 1000 * 60)
    public void executePrevisao() {
        log.info("Executando scheduler...");
        bairroService.findAll().forEach(bairro -> {
            log.info("Executando bairro {}", bairro.getNome());
            previsaoApiService.savePrevisao(bairro);
            System.out.printf("Previsão salva no bairro %s%n", bairro.getNome());
        });
    }

}
