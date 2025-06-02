package com.gsnimbus.api.controller;

import com.gsnimbus.api.dto.localizacao.LocalizacaoDto;
import com.gsnimbus.api.model.Localizacao;
import com.gsnimbus.api.service.LocalizacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localizacoes")
@RequiredArgsConstructor
@Tag(name = "Localizações", description = "Endpoints para gerenciamento de localizações")
public class LocalizacaoController {

    private final LocalizacaoService localizacaoService;

    @GetMapping
    public ResponseEntity<List<Localizacao>> findAll() {
        return ResponseEntity.ok(localizacaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Localizacao> findById(@PathVariable Long id) {
        return ResponseEntity.ok(localizacaoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Localizacao> save(@RequestBody LocalizacaoDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(localizacaoService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Localizacao> update(@RequestBody LocalizacaoDto dto, @PathVariable Long id){
        return ResponseEntity.ok(localizacaoService.update(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        localizacaoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

