package com.gsnimbus.api.service;

import com.gsnimbus.api.dto.endereco.EnderecoDto;
import com.gsnimbus.api.dto.endereco.EnderecoMapper;
import com.gsnimbus.api.dto.endereco.NovoEnderecoDto;
import com.gsnimbus.api.dto.endereco.bairro.BairroDto;
import com.gsnimbus.api.exception.ResourceNotFoundException;
import com.gsnimbus.api.model.*;
import com.gsnimbus.api.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoMapper enderecoMapper;
    private final PaisService paisService;
    private final EstadoService estadoService;
    private final CidadeService cidadeService;
    private final BairroService bairroService;
    private final LocalizacaoService localizacaoService;

    @Cacheable(value = "findAllEndereco")
    @Transactional(readOnly = true)
    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    @Cacheable(value = "findByIdEndereco", key = "#id")
    @Transactional(readOnly = true)
    public Endereco findById(Long id) {
        return enderecoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Endereço não foi encontrado"));
    }

    @Transactional
    public Endereco save(EnderecoDto dto) {
        return enderecoRepository.save(enderecoMapper.toEntity(dto));
    }

    public Endereco saveFull(NovoEnderecoDto dto) {
        Localizacao localizacao = localizacaoService.saveLocalizacaoWithGeocoding(dto.toString());
        Pais pais = paisService.saveOrFind(dto.getPais());
        Estado estado = estadoService.saveOrFind(dto.getEstado(), pais.getIdPais());
        Cidade cidade = cidadeService.saveOrFind(dto.getCidade(), estado.getIdEstado());
        Bairro bairro = bairroService.saveOrFind(new BairroDto(dto.getBairro(), cidade.getIdCidade(), localizacao.getId()));
//        Bairro bairro = bairroService.save(dto.getBairro(), cidade.getIdCidade(), localizacao.getId());
        log.info("Salvando bairro: {}", bairro);
        EnderecoDto enderecoDto = new EnderecoDto(dto.getNomeLogradouro(), dto.getNumLogradouro(), bairro.getId(), dto.getCep());
        log.info("Salvando endereco: {}", enderecoDto);
        return enderecoRepository.save(enderecoMapper.toEntity(enderecoDto));
    }

    @Transactional
    public Endereco update(EnderecoDto dto, Long id) {
        Endereco endereco = findById(id);
        enderecoMapper.updateEntityFromDto(dto, endereco);
        return enderecoRepository.save(endereco);
    }

    @Transactional
    public void delete(Long id) {
        Endereco endereco = findById(id);
        enderecoRepository.delete(endereco);
    }

    @CacheEvict(value = {
            "findAllEndereco", "findByIdEndereco"
    })
    public void cleanCache() {
        System.out.println("Limpando cache...");
    }


}
