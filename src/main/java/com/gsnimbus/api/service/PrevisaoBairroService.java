package com.gsnimbus.api.service;

import com.gsnimbus.api.dto.previsao.api.PrevisaoMapper;
import com.gsnimbus.api.dto.previsao.bairro.PrevisaoBairroDTO;
import com.gsnimbus.api.dto.previsao.bairro.PrevisaoBairroMapper;
import com.gsnimbus.api.exception.ResourceNotFoundException;
import com.gsnimbus.api.model.Previsao;
import com.gsnimbus.api.model.PrevisaoBairro;
import com.gsnimbus.api.repository.PrevisaoBairroRepository;
import com.gsnimbus.api.repository.PrevisaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrevisaoBairroService {
    private final PrevisaoBairroRepository previsaoBairroRepository;
    private final PrevisaoBairroMapper previsaoBairroMapper;

    @Cacheable(value = "findAllPrevisaoBairro")
    @Transactional(readOnly = true)
    public List<PrevisaoBairro> findAll(){
        return previsaoBairroRepository.findAll();
    }


    @Cacheable(value = "findByIdPrevisaoBairro")
    @Transactional(readOnly = true)
    public PrevisaoBairro findById(Long id){
        return previsaoBairroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Previsão por bairro não encontrada!"));
    }

    @Cacheable(value = "findLastPrevisaoByBairro", key = "#idBairro")
    @Transactional(readOnly = true)
    public Previsao findLastPrevisaoByBairro(Long idBairro){
        return previsaoBairroRepository.findLastPrevisaoByBairro(idBairro);
    }


    @Transactional
    public PrevisaoBairro save(PrevisaoBairroDTO dto){
        cleanCache();
        return previsaoBairroRepository.save(previsaoBairroMapper.toEntity(dto));
    }

    public PrevisaoBairro save(Long idBairro, Long idPrevisao){
        cleanCache();
        return previsaoBairroRepository.save(previsaoBairroMapper.toEntity(new PrevisaoBairroDTO(idBairro, idPrevisao)));
    }


    @Transactional
    public PrevisaoBairro update(PrevisaoBairroDTO dto, Long id){
        PrevisaoBairro previsaoBairro = findById(id);
        cleanCache();
        previsaoBairroMapper.updateEntityFromDto(dto, previsaoBairro);
        return previsaoBairroRepository.save(previsaoBairro);
    }


    @Transactional
    public void delete(Long id){
        PrevisaoBairro previsaoBairro = findById(id);
        previsaoBairroRepository.delete(previsaoBairro);
        cleanCache();
    }


    @CacheEvict(value = {
            "findAllPrevisaoBairro", "findByIdPrevisaoBairro"
    })
    public void cleanCache() {
        System.out.println("Limpando cache...");
    }


}
