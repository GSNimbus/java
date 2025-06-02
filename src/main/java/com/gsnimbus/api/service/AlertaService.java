package com.gsnimbus.api.service;

import com.gsnimbus.api.dto.alerta.AlertaDTO;
import com.gsnimbus.api.dto.alerta.AlertaMapper;
import com.gsnimbus.api.exception.ResourceNotFoundException;
import com.gsnimbus.api.model.Alerta;
import com.gsnimbus.api.repository.AlertaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertaService {
    private final AlertaRepository alertaRepository;
    private final AlertaMapper alertaMapper;

    @Cacheable(value = "findAllAlerta")
    @Transactional(readOnly = true)
    public List<Alerta> findAll(){
        return alertaRepository.findAll();
    }

    @Cacheable(value = "findByIdAlerta", key = "#id")
    @Transactional(readOnly = true)
    public Alerta findById(Long id){
        return alertaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Alerta não encontrado!"));
    }

    @Transactional
    public Alerta save(AlertaDTO dto) {
        cleanCache();
        Alerta alerta = alertaMapper.toEntity(dto);
        return alertaRepository.save(alerta);
    }

    @Transactional
    public Alerta update(AlertaDTO dto, Long id){
        cleanCache();
        Alerta alerta = findById(id);
        alertaMapper.updateEntityFromDto(dto, alerta);
        return alertaRepository.save(alerta);
    }

    @Transactional
    public void delete(Long id){
        cleanCache();
        Alerta alerta = findById(id);
        alertaRepository.delete(alerta);
    }

    @CacheEvict(value = {
            "findAllAlerta", "findByIdAlerta"
    }, allEntries = true)
    public void cleanCache(){
        System.out.println("Limpando cache de alerta...");
    }

}

