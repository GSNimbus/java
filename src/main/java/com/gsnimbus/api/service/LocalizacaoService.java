package com.gsnimbus.api.service;

import com.gsnimbus.api.dto.localizacao.LocalizacaoDto;
import com.gsnimbus.api.dto.localizacao.LocalizacaoMapper;
import com.gsnimbus.api.exception.ResourceNotFoundException;
import com.gsnimbus.api.model.Localizacao;
import com.gsnimbus.api.repository.LocalizacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocalizacaoService {
    private final LocalizacaoRepository localizacaoRepository;
    private final LocalizacaoMapper localizacaoMapper;

    @Cacheable(value = "findAllLocalizacao")
    @Transactional(readOnly = true)
    public List<Localizacao> findAll(){
        return localizacaoRepository.findAll();
    }

    @Cacheable(value = "findByIdLocalizacao", key = "#id")
    @Transactional(readOnly = true)
    public Localizacao findById(Long id){
        return localizacaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Localização não encontrada!"));
    }

    @Transactional
    public Localizacao save(LocalizacaoDto dto) {
        cleanCache();
        return localizacaoRepository.save(localizacaoMapper.toEntity(dto));
    }

    @Transactional
    public Localizacao update(LocalizacaoDto dto, Long id){
        cleanCache();
        Localizacao localizacao = findById(id);
        localizacaoMapper.updateEntityFromDto(dto, localizacao);
        return localizacaoRepository.save(localizacao);
    }

    @Transactional
    public void delete(Long id){
        cleanCache();
        Localizacao localizacao = findById(id);
        localizacaoRepository.delete(localizacao);
    }

    @CacheEvict(value = {
            "findAllLocalizacao", "findByIdLocalizacao"
    }, allEntries = true)
    public void cleanCache(){
        System.out.println("Limpando cache de localização...");
    }

}

