package com.gsnimbus.api.service;

import com.gsnimbus.api.dto.alerta.bairro.AlertaBairroDTO;
import com.gsnimbus.api.dto.alerta.bairro.AlertaBairroMapper;
import com.gsnimbus.api.exception.ResourceNotFoundException;
import com.gsnimbus.api.model.Alerta;
import com.gsnimbus.api.model.AlertaBairro;
import com.gsnimbus.api.model.Bairro;
import com.gsnimbus.api.repository.AlertaBairroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertaBairroService {
    private final AlertaBairroRepository alertaBairroRepository;
    private final AlertaBairroMapper alertaBairroMapper;
    private final BairroService bairroService;

    @Cacheable(value = "findAllAlertaBairro")
    @Transactional(readOnly = true)
    public List<AlertaBairro> findAll() {
        return alertaBairroRepository.findAll();
    }

    @Cacheable(value = "findAllAlertaByBairro", key = "#idBairro")
    @Transactional(readOnly = true)
    public List<Alerta> findAllAlertaByBairro(Long idBairro) {
        Bairro bairro = bairroService.findById(idBairro);
        return alertaBairroRepository.findAllAlertaByBairro(bairro);
    }

    @Cacheable(value = "findAllAlertaByBairroToday", key = "#idBairro")
    @Transactional(readOnly = true)
    public List<Alerta> findAllAlertaByBairroToday(Long idBairro){
        LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
        LocalDateTime startOfDay = now.toLocalDate().atStartOfDay().atZone(ZoneId.of("America/Sao_Paulo")).toLocalDateTime();
        LocalDateTime endOfDay = now.toLocalDate().atTime(23, 59, 59, 999999999).atZone(ZoneId.of("America/Sao_Paulo")).toLocalDateTime();
        return alertaBairroRepository.findAllAlertaByBairroAndDate(idBairro, startOfDay, endOfDay);
    }

    @Cacheable(value = "findByIdAlertaBairro")
    @Transactional(readOnly = true)
    public AlertaBairro findById(Long id) {
        return alertaBairroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alerta por bairro não encontrado!"));
    }

    @Cacheable(value = "findLastAlertaByBairro", key = "#idBairro")
    @Transactional(readOnly = true)
    public Alerta findLastAlertaByBairro(Long idBairro) {
        return alertaBairroRepository.findLastAlertaByBairro(idBairro);
    }

    @Transactional
    public AlertaBairro save(AlertaBairroDTO dto) {
        cleanCache();
        return alertaBairroRepository.save(alertaBairroMapper.toEntity(dto));
    }

    public AlertaBairro save(Long idBairro, Long idAlerta) {
        cleanCache();
        return alertaBairroRepository.save(alertaBairroMapper.toEntity(new AlertaBairroDTO(idBairro, idAlerta)));
    }

    @Transactional
    public AlertaBairro update(AlertaBairroDTO dto, Long id) {
        AlertaBairro alertaBairro = findById(id);
        cleanCache();
        alertaBairroMapper.updateEntityFromDto(dto, alertaBairro);
        return alertaBairroRepository.save(alertaBairro);
    }

    @Transactional
    public void delete(Long id) {
        AlertaBairro alertaBairro = findById(id);
        alertaBairroRepository.delete(alertaBairro);
        cleanCache();
    }

    @CacheEvict(value = {
            "findAllAlertaBairro", "findByIdAlertaBairro", "findAllAlertaByBairro",
            "findLastAlertaByBairro", "findAllAlertaByBairroToday"
    }, allEntries = true)
    public void cleanCache() {
        System.out.println("Limpando cache de alertas por bairro...");
    }
}
