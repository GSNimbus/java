package com.gsnimbus.api.service;

import com.gsnimbus.api.dto.endereco.bairro.BairroDto;
import com.gsnimbus.api.dto.endereco.bairro.BairroMapper;
import com.gsnimbus.api.exception.ResourceNotFoundException;
import com.gsnimbus.api.model.Alerta;
import com.gsnimbus.api.model.Bairro;
import com.gsnimbus.api.model.Cidade;
import com.gsnimbus.api.model.Localizacao;
import com.gsnimbus.api.model.Previsao;
import com.gsnimbus.api.repository.BairroRepository;
import com.gsnimbus.api.repository.CidadeRepository;
import com.gsnimbus.api.repository.LocalizacaoRepository;
import com.gsnimbus.api.service.events.BairroCriadoEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BairroService {
    private final BairroRepository bairroRepository;
    private final BairroMapper bairroMapper;
    private final CidadeRepository cidadeRepository;
    private final LocalizacaoRepository localizacaoRepository;
    private final ApplicationEventPublisher publisher;

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

    public Bairro findByName(String nomeBairro){
        return bairroRepository.findByNomeIgnoreCase(nomeBairro).orElse(null);
    }

    /**
     * Busca um bairro pela sua localização
     * @param localizacao Localização para buscar o bairro
     * @return Bairro encontrado ou null se não existir
     */
    @Transactional(readOnly = true)
    public Bairro findByLocalizacao(Localizacao localizacao) {
        if (localizacao == null || localizacao.getId() == null) {
            return null;
        }

        return bairroRepository.findByIdLocalizacao(localizacao);
    }

    /**
     * Salva um novo bairro ou retorna um existente com o mesmo nome e cidade
     * Ao salvar, gera alertas e previsões para o bairro
     * @param dto Dados do bairro a ser salvo
     * @return Bairro salvo ou encontrado
     */
    @Transactional
    public Bairro saveOrFind(BairroDto dto) {
        cleanCache();
        Optional<Bairro> bairroExistente = bairroRepository.findByNomeIgnoreCaseAndIdCidade_IdCidade(dto.getNome(), dto.getIdCidade());

        if (bairroExistente.isPresent()) {
            return bairroExistente.get();
        } else {
            Bairro novoBairro = bairroMapper.toEntity(dto);
            Cidade cidade = cidadeRepository.findById(dto.getIdCidade())
                    .orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrada com ID: " + dto.getIdCidade() + " ao tentar salvar o bairro " + dto.getNome()));
            Localizacao localizacao = localizacaoRepository.findById(dto.getIdLocalizacao())
                    .orElseThrow(() -> new ResourceNotFoundException("Localização não encontrada com ID: " + dto.getIdLocalizacao() + " ao tentar salvar o bairro " + dto.getNome()));

            novoBairro.setIdCidade(cidade);
            novoBairro.setIdLocalizacao(localizacao);

            Bairro bairro = bairroRepository.save(novoBairro);
            publisher.publishEvent(new BairroCriadoEvent(novoBairro));
            return bairro;
        }
    }

    @Transactional
    public Bairro save(BairroDto dto) {
        return saveOrFind(dto);
    }

    @Transactional
    public Bairro save(String nomeBairro, Long idCidade, Long idLocalizacao) {
        cleanCache();
        return saveOrFind(new BairroDto(nomeBairro, idCidade, idLocalizacao));
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

