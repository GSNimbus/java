package com.gsnimbus.api.service;

import com.gsnimbus.api.dto.endereco.pais.PaisDto;
import com.gsnimbus.api.dto.endereco.pais.PaisMapper;
import com.gsnimbus.api.exception.ResourceNotFoundException;
import com.gsnimbus.api.model.Pais;
import com.gsnimbus.api.repository.PaisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaisService {
    private final PaisRepository paisRepository;
    private final PaisMapper paisMapper;

    @Cacheable(value = "findAllPais")
    @Transactional(readOnly = true)
    public List<Pais> findAll(){
        return paisRepository.findAll();
    }

    @Cacheable(value = "findByIdPais", key = "#id")
    @Transactional(readOnly = true)
    public Pais findById(Long id){
        return paisRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("País não encontrado!"));
    }

    @Transactional
    public Pais save(PaisDto dto) {
        cleanCache();
        return paisRepository.save(paisMapper.toEntity(dto));
    }

    @Transactional
    public Pais update(PaisDto dto, Long id){
        cleanCache();
        Pais pais = findById(id);
        paisMapper.updateEntityFromDto(dto, pais);
        return paisRepository.save(pais);
    }

    @Transactional
    public void delete(Long id){
        cleanCache();
        Pais pais = findById(id);
        paisRepository.delete(pais);
    }

    @CacheEvict(value = {
            "findAllPais", "findByIdPais"
    })
    public void cleanCache(){
        System.out.println("Limpando cache...");
    }

}
