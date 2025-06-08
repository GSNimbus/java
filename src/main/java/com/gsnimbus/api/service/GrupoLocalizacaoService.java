package com.gsnimbus.api.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsnimbus.api.dto.localizacao.grupo.CasaGrupoProjection;
import com.gsnimbus.api.dto.localizacao.grupo.GrupoLocalizacaoDto;
import com.gsnimbus.api.dto.localizacao.grupo.GrupoLocalizacaoMapper;
import com.gsnimbus.api.exception.ResourceNotFoundException;
import com.gsnimbus.api.model.GrupoLocalizacao;
import com.gsnimbus.api.repository.GrupoLocalizacaoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GrupoLocalizacaoService {
    private final GrupoLocalizacaoRepository grupoLocalizacaoRepository;
    private final GrupoLocalizacaoMapper grupoLocalizacaoMapper;

    @Cacheable(value = "findAllGrupoLocalizacao")
    @Transactional(readOnly = true)
    public List<GrupoLocalizacao> findAll(){
        return grupoLocalizacaoRepository.findAll();
    }

    @Cacheable(value = "findGrupoLocalizacaoById", key = "#id")
    @Transactional(readOnly = true)
    public GrupoLocalizacao findById(Long id){
        return grupoLocalizacaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Grupo de localização não encontrado"));
    }

    @Cacheable(value ="findAllByUsuario", key = "#idUsuario")
    @Transactional(readOnly = true)
    public List<GrupoLocalizacao> findAllByUsuario(Long idUsuario){
        return grupoLocalizacaoRepository.findAllByUsuario_Id(idUsuario);
    }

    @Cacheable(value = "findHomeUsuario", key = "idUsuario")
    @Transactional(readOnly = true)
    public CasaGrupoProjection findHomeUsuario(Long idUsuario) {
        List<CasaGrupoProjection> projections = grupoLocalizacaoRepository.findProjectionsByUsuarioIdOrderedById(idUsuario);
        if (projections.isEmpty()){
            return null; 
        }
        return projections.get(0);
    }

    @Transactional
    public GrupoLocalizacao save(GrupoLocalizacaoDto dto){
        cleanCache();
        return grupoLocalizacaoRepository.save(grupoLocalizacaoMapper.toEntity(dto));
    }

    @Transactional
    public GrupoLocalizacao update(GrupoLocalizacaoDto dto, Long id){
        GrupoLocalizacao grupoLocalizacao = findById(id);
        grupoLocalizacaoMapper.updateEntityFromDto(dto, grupoLocalizacao);
        cleanCache();
        return grupoLocalizacaoRepository.save(grupoLocalizacao);
    }

    @Transactional
    public void delete(Long id) {
        GrupoLocalizacao grupoLocalizacao = findById(id);
        grupoLocalizacaoRepository.delete(grupoLocalizacao);
        cleanCache();
    }

    @CacheEvict(value = {
            "findAllGrupoLocalizacao", "findGrupoLocalizacaoById", "findAllByUsuario", "findHomeUsuario"
    })
    public void cleanCache(){
        System.out.println("Limpando cache...");
    }


}
