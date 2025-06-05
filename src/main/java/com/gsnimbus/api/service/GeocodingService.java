package com.gsnimbus.api.service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsnimbus.api.dto.geocoding.GeocodingApiDto;
import com.gsnimbus.api.dto.geocoding.ReverseGeocodingApiDto;
import com.gsnimbus.api.dto.localizacao.LocalizacaoDto;
import com.gsnimbus.api.exception.ApiFetchErrorException;
import com.gsnimbus.api.exception.ConversionErrorException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class GeocodingService {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Value("${locationiq.api.key}")
    private String apiKey;

    /**
     * Realiza a busca de coordenadas geográficas a partir de um endereço
     *
     * @param endereco endereço a ser buscado (ex: "Avenida Taruma 350 SaoPaulo Brasil")
     * @return o primeiro resultado da busca como GeocodingApiDto
     */
    public GeocodingApiDto getCoordinatesFromAddress(String endereco) {
        log.info("Iniciando geocoding para o endereço: {}", endereco);

        String response;
        try {
            response = fetchGeocodingData(endereco);
        } catch (IOException | InterruptedException e) {
            log.error("Erro ao chamar serviço de geocoding: {}", e.getMessage());
            throw new ApiFetchErrorException("Erro ao chamar serviço de geocoding");
        }

        if (response == null || response.isEmpty()) {
            throw new ApiFetchErrorException("Response vazio do serviço de geocoding!");
        }

        log.info("Comunicação com a API de geocoding bem sucedida!");
        List<GeocodingApiDto> geocodingResults;

        try {
            log.info("Começando mapping de json...");
            // A API retorna uma lista de resultados
            GeocodingApiDto[] geocodingArray = MAPPER.readValue(response, GeocodingApiDto[].class);
            geocodingResults = Arrays.asList(geocodingArray);
        } catch (JsonProcessingException e) {
            log.error("Erro ao converter o JSON: {}", e.getMessage());
            throw new ConversionErrorException("Erro ao converter o JSON da resposta de geocoding!");
        }

        if (geocodingResults == null || geocodingResults.isEmpty()) {
            throw new ConversionErrorException("Nenhum resultado encontrado para o endereço informado!");
        }

        log.info("Mapping bem-sucedido! Encontrados {} resultados", geocodingResults.size());
        log.info("Retornando o primeiro resultado da lista");
        // Retorna apenas o primeiro resultado conforme solicitado
        return geocodingResults.getFirst();
    }

    /**
     * Realiza a busca de endereço a partir de coordenadas geográficas
     *
     * @param localizacaoDto objeto contendo latitude e longitude
     * @return o resultado da busca como ReverseGeocodingApiDto
     */
    public ReverseGeocodingApiDto getAddressFromLocation(LocalizacaoDto localizacaoDto) {
        log.info("Iniciando geocoding reverso para latitude: {} e longitude: {}",
                localizacaoDto.getLatitude(), localizacaoDto.getLongitude());

        String response;
        try {
            response = fetchReverseGeocodingData(localizacaoDto);
        } catch (IOException | InterruptedException e) {
            log.error("Erro ao chamar serviço de geocoding reverso: {}", e.getMessage());
            throw new ApiFetchErrorException("Erro ao chamar serviço de geocoding reverso");
        }

        if (response == null || response.isEmpty()) {
            throw new ApiFetchErrorException("Response vazio do serviço de geocoding reverso!");
        }

        log.info("Comunicação com a API de geocoding reverso bem sucedida!");

        try {
            log.info("Começando mapping de json...");
            ReverseGeocodingApiDto result = MAPPER.readValue(response, ReverseGeocodingApiDto.class);
            log.info("Mapping bem-sucedido!");
            return result;
        } catch (JsonProcessingException e) {
            log.error("Erro ao converter o JSON: {}", e.getMessage());
            throw new ConversionErrorException("Erro ao converter o JSON da resposta de geocoding reverso!");
        }
    }

    /**
     * Realiza a requisição HTTP para a API de geocoding
     *
     * @param endereco endereço a ser buscado
     * @return string contendo a resposta JSON da API
     */
    private String fetchGeocodingData(String endereco) throws IOException, InterruptedException {
        // Codifica o endereço para uso na URL
        String encodedAddress = URLEncoder.encode(endereco, StandardCharsets.UTF_8);

        String baseUrl = "https://us1.locationiq.com/v1/search";
        String params = String.format("?key=%s&q=%s&format=json", apiKey, encodedAddress);

        String url = baseUrl + params;
        log.info("URL de requisição: {}", url);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            log.error("Erro na requisição: Status code {}", response.statusCode());
            throw new ApiFetchErrorException("Erro na requisição à API de geocoding. Status code: " + response.statusCode());
        }

        return response.body();
    }

    /**
     * Realiza a requisição HTTP para a API de geocoding reverso
     *
     * @param localizacaoDto objeto contendo latitude e longitude
     * @return string contendo a resposta JSON da API
     */
    private String fetchReverseGeocodingData(LocalizacaoDto localizacaoDto) throws IOException, InterruptedException {
        String baseUrl = "https://us1.locationiq.com/v1/reverse";

        // Formatar com exatamente 6 casas decimais para garantir o formato correto
//        String latFormatted = String.format("%.6f", localizacaoDto.getLatitude());
//        String lonFormatted = String.format("%.6f", localizacaoDto.getLongitude());

        String params = String.format("?key=%s&lat=%s&lon=%s&format=json",
                apiKey,
                localizacaoDto.getLatitude(),
                localizacaoDto.getLongitude());

        String url = baseUrl + params;
        log.info("URL de requisição: {}", url);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            log.error("Erro na chamada da API. Status code: {}", response.statusCode());
            throw new ApiFetchErrorException("Erro ao chamar API de geocoding reverso!");
        }

        return response.body();
    }
}

