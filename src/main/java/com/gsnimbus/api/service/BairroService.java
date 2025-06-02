package com.gsnimbus.api.service;

import com.gsnimbus.api.dto.endereco.bairro.BairroDto;
import com.gsnimbus.api.dto.endereco.bairro.BairroMapper;
import com.gsnimbus.api.exception.ResourceNotFoundException;
import com.gsnimbus.api.model.Bairro;
import com.gsnimbus.api.repository.BairroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BairroService {
    private final BairroRepository bairroRepository;
    private final BairroMapper bairroMapper;

    @Cacheable(value = "findAllBairro")
    @Transactional(readOnly = true)
    public List<Bairro> findAll(){
        return bairroRepository.findAll();
    }

    @Cacheable(value = "findByIdBairro", key = "#id")
    @Transactional(readOnly = true)
    public Bairro findById(Long id){
        return bairroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bairro não encontrado!"));
    }

    @Transactional
    public Bairro save(BairroDto dto) {
        cleanCache();
        return bairroRepository.save(bairroMapper.toEntity(dto));
    }

    @Transactional
    public Bairro update(BairroDto dto, Long id){
        cleanCache();
        Bairro bairro = findById(id);
        bairroMapper.updateEntityFromDto(dto, bairro);
        return bairroRepository.save(bairro);
    }

    @Transactional
    public void delete(Long id){
        cleanCache();
        Bairro bairro = findById(id);
        bairroRepository.delete(bairro);
    }

    @CacheEvict(value = {
            "findAllBairro", "findByIdBairro"
    }, allEntries = true)
    public void cleanCache(){
        System.out.println("Limpando cache de bairro...");
    }

}

