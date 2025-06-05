package com.gsnimbus.api.service;

import com.gsnimbus.api.dto.geocoding.GeocodingApiDto;
import com.gsnimbus.api.dto.geocoding.ReverseGeocodingApiDto;
import com.gsnimbus.api.dto.localizacao.LocalizacaoDto;
import com.gsnimbus.api.dto.localizacao.LocalizacaoMapper;
import com.gsnimbus.api.dto.localizacao.LocalizacaoNovaProjection;
import com.gsnimbus.api.exception.ResourceNotFoundException;
import com.gsnimbus.api.model.*;
import com.gsnimbus.api.repository.LocalizacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocalizacaoService {
    private final LocalizacaoRepository localizacaoRepository;
    private final LocalizacaoMapper localizacaoMapper;
    private final GeocodingService geocodingService;
    private final BairroService bairroService;
    private final PaisService paisService;
    private final EstadoService estadoService;
    private final CidadeService cidadeService;

    @Cacheable(value = "findAllLocalizacao")
    @Transactional(readOnly = true)
    public List<Localizacao> findAll() {
        return localizacaoRepository.findAll();
    }

    @Cacheable(value = "findByIdLocalizacao", key = "#id")
    @Transactional(readOnly = true)
    public Localizacao findById(Long id) {
        return localizacaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Localização não encontrada!"));
    }

    @Transactional(readOnly = true)
    public Localizacao findByLocalizacao(LocalizacaoDto dto) {
        return localizacaoRepository.findByLocalizacao(dto.getLatitude(), dto.getLongitude()).orElse(null);
    }

    @Transactional
    public LocalizacaoNovaProjection saveOrFind(LocalizacaoDto dto) {
        ReverseGeocodingApiDto reverseGeocodingApiDto = geocodingService.getAddressFromLocation(dto);
        String bairroApi = reverseGeocodingApiDto.getAddress().getSuburb();
        Bairro bairro = bairroService.findByName(bairroApi);

        if (bairro != null) {
            return new LocalizacaoNovaProjection(bairro);
        }

        Pais pais = paisService.saveOrFind(reverseGeocodingApiDto.getAddress().getCountry());
        Estado estado = estadoService.saveOrFind(reverseGeocodingApiDto.getAddress().getState());
        Cidade cidade = cidadeService.saveOrFind(reverseGeocodingApiDto.getAddress().getCity(), estado.getIdEstado());
        Localizacao localizacao = save(dto);
        bairro = bairroService.save(bairroApi, cidade.getIdCidade(), localizacao.getId());
        cleanCache();
        return new LocalizacaoNovaProjection(bairro);
    }

    @Transactional
    public Localizacao save(LocalizacaoDto dto) {
        cleanCache();
        return localizacaoRepository.save(localizacaoMapper.toEntity(dto));
    }


    public ReverseGeocodingApiDto testeGeoReversa(LocalizacaoDto dto) {
        return geocodingService.getAddressFromLocation(dto);
    }

    @Transactional
    public Localizacao saveLocalizacaoWithGeocoding(String endereco) {
        cleanCache();
        GeocodingApiDto geocodingApiDto = geocodingService.getCoordinatesFromAddress(endereco);

        LocalizacaoDto localizacaoDto = new LocalizacaoDto(
                new BigDecimal(geocodingApiDto.getLon()).setScale(5, RoundingMode.HALF_UP),
                new BigDecimal(geocodingApiDto.getLat()).setScale(5, RoundingMode.HALF_UP)
        );
        return save(localizacaoDto);
    }

    @Transactional
    public Localizacao update(LocalizacaoDto dto, Long id) {
        cleanCache();
        Localizacao localizacao = findById(id);
        localizacaoMapper.updateEntityFromDto(dto, localizacao);
        return localizacaoRepository.save(localizacao);
    }

    @Transactional
    public void delete(Long id) {
        cleanCache();
        Localizacao localizacao = findById(id);
        localizacaoRepository.delete(localizacao);
    }

    @CacheEvict(value = {
            "findAllLocalizacao", "findByIdLocalizacao"
    }, allEntries = true)
    public void cleanCache() {
        System.out.println("Limpando cache de localização...");
    }

}

