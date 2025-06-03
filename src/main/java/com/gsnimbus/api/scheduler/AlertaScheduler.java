package com.gsnimbus.api.scheduler;

import com.gsnimbus.api.model.Alerta;
import com.gsnimbus.api.model.Previsao;
import com.gsnimbus.api.service.AlertaAIService;
import com.gsnimbus.api.service.BairroService;
import com.gsnimbus.api.service.PrevisaoBairroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class AlertaScheduler {
    private final AlertaAIService alertaAIService;
    private final PrevisaoBairroService previsaoBairroService;
    private final BairroService bairroService;

//    @Scheduled(fixedRate = 60 * 1000 * 60, initialDelay = 1000 * 60 * 2)
//    public void executeAlerta(){
//        bairroService.findAll().forEach(bairro -> {
//            Previsao previsao = previsaoBairroService.findLastPrevisaoByBairro(bairro.getId());
//            log.info("Tá indo a previsão por ultimo, amém god! \n\n\nPrevisão por último: {}", previsao);
//            Alerta alerta = alertaAIService.processarAlerta(previsao, bairro.getId());
//            log.info("Alerta criado com sucesso: {}", alerta);
//            log.info("Bairro com o alerta: {}", bairro.getNome());
//        });
//    }
}
