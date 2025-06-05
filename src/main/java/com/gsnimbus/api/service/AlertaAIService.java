package com.gsnimbus.api.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.gsnimbus.api.dto.alerta.AlertaInputDto;
import com.gsnimbus.api.dto.previsao.api.PrevisaoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.gsnimbus.api.dto.alerta.AlertaDTO;
import com.gsnimbus.api.exception.ApiFetchErrorException;
import com.gsnimbus.api.exception.ConversionErrorException;
import com.gsnimbus.api.model.Alerta;
import com.gsnimbus.api.model.Previsao;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class AlertaAIService {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final ObjectMapper MAPPER = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    @Value("${ia.url}")
    private String ALERTA_API_URL;

    private final AlertaService alertaService;

    public Alerta processarAlerta(Previsao inputDto, Long idBairro) {
        log.info("Iniciando processamento de alerta com IA...");

        if (inputDto == null) {
            throw new IllegalArgumentException("Objeto Previsao não pode ser nulo para processar alerta para o bairro ID!");
        }

        String response;
        try {
            log.info("Enviando previsão para a API de alertas");
            response = enviarPrevisaoParaAPI(inputDto);
        } catch (IOException | InterruptedException e) {
            throw new ApiFetchErrorException("Erro ao comunicar com serviço de alerta: " + e.getMessage());
        }

        if (response == null) {
            throw new ApiFetchErrorException("Response vazio da API de alerta!");
        }

        log.info("Comunicação com API de alerta bem sucedida!");
        AlertaDTO alertaDTO;
        try {
            log.info("Convertendo resposta JSON para AlertaDTO...");
            alertaDTO = MAPPER.readValue(response, AlertaDTO.class);
            alertaDTO.setIdBairro(idBairro);
            log.info("Objeto AlertaDTO: {}", alertaDTO);
        } catch (IOException e) {
            throw new ConversionErrorException("Erro ao converter resposta JSON para AlertaDTO: " + e.getMessage());
        }

        return alertaService.save(alertaDTO);
    }



    private String enviarPrevisaoParaAPI(Previsao inputDto) throws IOException, InterruptedException {
        log.info("Preparando requisição para API de alertas...");

        String previsaoJson = MAPPER.writeValueAsString(inputDto);
        log.info("Objeto previsão serializado: {}", previsaoJson);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ALERTA_API_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(previsaoJson))
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        log.info("Status da resposta: {}", response.statusCode());

        if (response.statusCode() != 200) {
            throw new ApiFetchErrorException("Erro ao comunicar com API de alerta! StatusCode: " + response.statusCode());
        }

        log.info("Corpo da resposta: {}", response.body());
        return response.body();
    }
}
