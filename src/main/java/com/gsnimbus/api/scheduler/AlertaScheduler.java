package com.gsnimbus.api.scheduler;

import com.gsnimbus.api.service.AlertaService;
import com.gsnimbus.api.service.BairroService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

@Component
@RequiredArgsConstructor
public class AlertaScheduler {

    private final BairroService bairroService;
    private final AlertaService alertaService;


    @Scheduled(fixedRate = 3600 * 60)
    public void executeAlert(){
//        bairroService.findAll().stream().map((bairro) -> {
//
//        })
//        try (HttpClient httpClient = HttpClient.newHttpClient()) {
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(URI.create("https://api.exemplo.com/dados"))
//                    .GET()
//                    .header("Accept", "application/json")
//                    .build();
//        }
        System.out.println("O Gustavo é muito bonito né, fala sério!");

    }

}
