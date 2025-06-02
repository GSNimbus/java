package com.gsnimbus.api.service;

import com.gsnimbus.api.dto.endereco.estado.EstadoDto;
import com.gsnimbus.api.dto.endereco.estado.EstadoMapper;
import com.gsnimbus.api.exception.ResourceNotFoundException;
import com.gsnimbus.api.model.Estado;
import com.gsnimbus.api.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoService {
    private final EstadoRepository estadoRepository;
    private final EstadoMapper estadoMapper;

    @Cacheable(value = "findAllEstado")
    @Transactional(readOnly = true)
    public List<Estado> findAll(){
        return estadoRepository.findAll();
    }

    @Cacheable(value = "findByIdEstado", key = "#id")
    @Transactional(readOnly = true)
    public Estado findById(Long id){
        return estadoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado!"));
    }

    @Transactional
    public Estado save(EstadoDto dto) {
        cleanCache();
        return estadoRepository.save(estadoMapper.toEntity(dto));
    }

    @Transactional
    public Estado update(EstadoDto dto, Long id){
        cleanCache();
        Estado estado = findById(id);
        estadoMapper.updateEntityFromDto(dto, estado);
        return estadoRepository.save(estado);
    }

    @Transactional
    public void delete(Long id){
        cleanCache();
        Estado estado = findById(id);
        estadoRepository.delete(estado);
    }

    @CacheEvict(value = {
            "findAllEstado", "findByIdEstado"
    }, allEntries = true)
    public void cleanCache(){
        System.out.println("Limpando cache de estado...");
    }

}

