package com.gsnimbus.api.service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import com.gsnimbus.api.service.events.PrevisaoCriadaEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.gsnimbus.api.dto.previsao.api.PrevisaoDTO;
import com.gsnimbus.api.exception.ApiFetchErrorException;
import com.gsnimbus.api.exception.ConversionErrorException;
import com.gsnimbus.api.model.Bairro;
import com.gsnimbus.api.model.Localizacao;
import com.gsnimbus.api.model.Previsao;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class PrevisaoApiService {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final ObjectMapper MAPPER = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    private final PrevisaoService previsaoService;
    private final ApplicationEventPublisher publisher;

    private Previsao getPrevisao(Bairro bairro) {
        Localizacao localizacao = bairro.getIdLocalizacao();
        log.info("Obteve localização");
        String response;
        try {
            log.info("iniciando comunicação com a api");
            response = fetchWeather(localizacao);
        } catch (IOException | InterruptedException e) {
            throw new ApiFetchErrorException("Erro ao chamar serviço de metereologia");
        }
        if (response == null) {
            throw new ApiFetchErrorException("Response vazio!");
        }
        log.info("comunicação bem sucedida!");
        PrevisaoDTO previsaoDTO;
        try {
            log.info("começando mapping de json...");
             previsaoDTO = MAPPER.readValue(response, PrevisaoDTO.class);
             previsaoDTO.setIdBairro(bairro.getId());
        } catch (JsonProcessingException e) {
            throw new ConversionErrorException("Erro ao converter o JSON!");
        }

        log.info("Mapping bem-sucedido!");
        log.info("Salvando previsão no banco...");
        log.info("Objeto previsaoDTO: {} \n\n\n\n", previsaoDTO);
        Previsao previsao = previsaoService.save(previsaoDTO);
        publisher.publishEvent(new PrevisaoCriadaEvent(previsao, bairro.getId()));
        return previsao;
    }

    public Previsao savePrevisao(Bairro idBairro){
        log.info("iniciando previsao com bairro inteiro...");
        return getPrevisao(idBairro);
    }

    public String fetchWeather(Localizacao localizacao) throws IOException, InterruptedException {
        log.info("Latitude:  {}", localizacao.getLatitude());
        log.info("Longitude:  {}", localizacao.getLongitude());


        String baseUrl = "https://api.open-meteo.com/v1/forecast";
        String params = String.format(
                "?latitude=%s&longitude=%s"
                        + "&current=temperature_2m,relative_humidity_2m,apparent_temperature,surface_pressure,precipitation,rain,"
                        + "wind_gusts_10m,wind_speed_10m,weather_code"
                        + "&timezone=%s",
                localizacao.getLatitude(),
                localizacao.getLongitude(),
                URLEncoder.encode("America/Sao_Paulo", StandardCharsets.UTF_8)
        );

        String url = baseUrl + params;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        log.info("Status da resposta: {}", response.statusCode());
        if (response.statusCode() != 200){
            throw new ApiFetchErrorException("Erro ao puxar api!\nStatusCode: " + response.statusCode());
        }
        log.info("\n\n\nCorpo de resposta: {}\n\n\n", response.body());
        return response.body();
    }
}
