package com.gsnimbus.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsnimbus.api.dto.previsao.api.PrevisaoDTO;
import com.gsnimbus.api.exception.ApiFetchErrorException;
import com.gsnimbus.api.exception.ConversionErrorException;
import com.gsnimbus.api.model.Bairro;
import com.gsnimbus.api.model.Localizacao;
import com.gsnimbus.api.model.Previsao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
public class PrevisaoApiService {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final PrevisaoService previsaoService;
    private final BairroService bairroService;


    public Previsao savePrevisao(Long idBairro){
        Bairro bairro = bairroService.findById(idBairro);
        Localizacao localizacao = bairro.getIdLocalizacao();

        String response;
        try {
            response = fetchWeather(localizacao);
        } catch (IOException | InterruptedException e) {
            throw new ApiFetchErrorException("Erro ao chamar a api do Open Meteo");
        }
        if (response == null) {
            throw new ApiFetchErrorException("Response vazio!");
        }

        PrevisaoDTO previsaoDTO;
        try {
             previsaoDTO = MAPPER.readValue(response, PrevisaoDTO.class);
        } catch (JsonProcessingException e) {
            throw new ApiFetchErrorException("Erro ao chamar a api do Open Meteo");
        }

        if (previsaoDTO == null) {
            throw new ConversionErrorException("Erro ao converter o JSON!");
        }

        return previsaoService.save(previsaoDTO);
    }

    public String fetchWeather(Localizacao localizacao) throws IOException, InterruptedException {
        String url = String.format(
                "https://api.open-meteo.com/v1/forecast" +
                        "?latitude=%f" +
                        "&longitude=%f" +
                        "&current=temperature_2m,precipitation,rain,weather_code,wind_speed_10m,wind_gusts_10m,relative_humidity_2m" +
                        "&timezone=America%%2FSao_Paulo" +
                        "&forecast_days=1",
                localizacao.getLatitude(), localizacao.getLongitude()
        );
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
