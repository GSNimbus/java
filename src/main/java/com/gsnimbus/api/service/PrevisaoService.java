package com.gsnimbus.api.service;

import com.gsnimbus.api.dto.previsao.api.PrevisaoDTO;
import com.gsnimbus.api.dto.previsao.api.PrevisaoMapper;
import com.gsnimbus.api.exception.ResourceNotFoundException;
import com.gsnimbus.api.model.Bairro;
import com.gsnimbus.api.model.Previsao;
import com.gsnimbus.api.model.PrevisaoBairro;
import com.gsnimbus.api.model.Localizacao;
import com.gsnimbus.api.repository.PrevisaoRepository;
import com.gsnimbus.api.repository.BairroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrevisaoService {

    private final PrevisaoRepository previsaoRepository;
    private final PrevisaoMapper previsaoMapper;
    private final PrevisaoBairroService previsaoBairroService;
    private final BairroRepository bairroRepository;

    @Cacheable(value = "findAllPrevisao")
    @Transactional(readOnly = true)
    public List<Previsao> findAll(){
        return previsaoRepository.findAll();
    }

    @Cacheable(value = "findByIdPrevisao", key = "#id")
    @Transactional(readOnly = true)
    public Previsao findById(Long id){
        return previsaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Previsão não encontrada"));
    }

    @Transactional
    public Previsao save(PrevisaoDTO dto){
        cleanCache();
        return previsaoRepository.save(previsaoMapper.toEntity(dto));
    }

    @Transactional
    public Previsao save(PrevisaoDTO dto, Long idBairro){
        Previsao previsao = previsaoRepository.save(previsaoMapper.toEntity(dto));
        previsaoBairroService.save(previsao.getId(), idBairro);
        return previsao;
    }

    @Transactional
    public Previsao update(PrevisaoDTO dto, Long id){
        cleanCache();
        Previsao previsao = findById(id);
        previsaoMapper.updateEntityFromDto(dto, previsao);
        return previsaoRepository.save(previsao);
    }

    @Transactional
    public void delete(Long id){
        cleanCache();
        Previsao previsao = findById(id);
        previsaoRepository.delete(previsao);
    }



    @CacheEvict(value = {
            "findAllPrevisao", "findByIdPrevisao"
    })
    public void cleanCache(){
        System.out.println("Limpando cache...");
    }
}
