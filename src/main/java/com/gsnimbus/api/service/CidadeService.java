package com.gsnimbus.api.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsnimbus.api.dto.endereco.cidade.CidadeDto;
import com.gsnimbus.api.dto.endereco.cidade.CidadeMapper;
import com.gsnimbus.api.exception.ResourceNotFoundException;
import com.gsnimbus.api.model.Cidade;
import com.gsnimbus.api.repository.CidadeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CidadeService {
    private final CidadeRepository cidadeRepository;
    private final CidadeMapper cidadeMapper;

    @Cacheable(value = "findAllCidade")
    @Transactional(readOnly = true)
    public List<Cidade> findAll(){
        return cidadeRepository.findAll();
    }

    @Cacheable(value = "findByIdCidade", key = "#id")
    @Transactional(readOnly = true)
    public Cidade findById(Long id){
        return cidadeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrada!"));
    }

    @Transactional(readOnly = true)
    public Cidade findByName(String nome) {
        return cidadeRepository.findFirstByNmCidade(nome).orElse(null);
    }

    @Transactional
    public Cidade save(CidadeDto dto) {
        cleanCache();
        return cidadeRepository.save(cidadeMapper.toEntity(dto));
    }

    @Transactional
    public Cidade saveOrFind(String nome, Long idEstado) {
        Cidade cidadeSalva = findByName(nome);
        if (cidadeSalva != null) {
            return cidadeSalva;
        }

        CidadeDto dto = new CidadeDto(nome, idEstado);
        return save(dto);
    }

    @Transactional
    public Cidade update(CidadeDto dto, Long id){
        cleanCache();
        Cidade cidade = findById(id);
        cidadeMapper.updateEntityFromDto(dto, cidade);
        return cidadeRepository.save(cidade);
    }

    @Transactional
    public void delete(Long id){
        cleanCache();
        Cidade cidade = findById(id);
        cidadeRepository.delete(cidade);
    }

    @CacheEvict(value = {
            "findAllCidade", "findByIdCidade"
    }, allEntries = true)
    public void cleanCache(){
        System.out.println("Limpando cache de cidade...");
    }

}

