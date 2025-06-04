package com.gsnimbus.api.controller;

import java.util.List;

import com.gsnimbus.api.service.events.BairroCriadoEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gsnimbus.api.dto.endereco.bairro.BairroDto;
import com.gsnimbus.api.model.Bairro;
import com.gsnimbus.api.service.AlertaAIService;
import com.gsnimbus.api.service.AlertaBairroService;
import com.gsnimbus.api.service.BairroService;
import com.gsnimbus.api.service.PrevisaoApiService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bairro")
@RequiredArgsConstructor
@Tag(name = "Bairros", description = "Endpoints para gerenciamento de bairros")
public class BairroController {

    private final BairroService bairroService;
    private final ApplicationEventPublisher publisher;


    @GetMapping
    public ResponseEntity<List<Bairro>> findAll() {
        return ResponseEntity.ok(bairroService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bairro> findById(@PathVariable Long id) {
        return ResponseEntity.ok(bairroService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Bairro> save(@RequestBody BairroDto dto) {
        Bairro bairro = bairroService.findByName(dto.getNome());
        if (bairro != null){
            return ResponseEntity.ok(bairro);
        }
        Bairro novoBairro = bairroService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoBairro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bairro> update(@RequestBody BairroDto dto, @PathVariable Long id){
        return ResponseEntity.ok(bairroService.update(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        bairroService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

