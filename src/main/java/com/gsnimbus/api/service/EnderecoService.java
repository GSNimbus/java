package com.gsnimbus.api.service;

import com.gsnimbus.api.dto.endereco.EnderecoDto;
import com.gsnimbus.api.dto.endereco.EnderecoMapper;
import com.gsnimbus.api.exception.ResourceNotFoundException;
import com.gsnimbus.api.model.Endereco;
import com.gsnimbus.api.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoMapper enderecoMapper;

    @Cacheable(value = "findAllEndereco")
    @Transactional(readOnly = true)
    public List<Endereco> findAll(){
        return enderecoRepository.findAll();
    }

    @Cacheable(value = "findByIdEndereco", key = "#id")
    @Transactional(readOnly = true)
    public Endereco findById(Long id){
        return enderecoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Endereço não foi encontrado"));
    }

    @Transactional
    public Endereco save(EnderecoDto dto){
        return enderecoRepository.save(enderecoMapper.toEntity(dto));
    }

    @Transactional
    public Endereco update(EnderecoDto dto, Long id){
        Endereco endereco = findById(id);
        enderecoMapper.updateEntityFromDto(dto, endereco);
        return enderecoRepository.save(endereco);
    }

    @Transactional
    public void delete (Long id){
        Endereco endereco = findById(id);
        enderecoRepository.delete(endereco);
    }



}
