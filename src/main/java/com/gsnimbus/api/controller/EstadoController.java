package com.gsnimbus.api.controller;

import com.gsnimbus.api.dto.endereco.estado.EstadoDto;
import com.gsnimbus.api.model.Estado;
import com.gsnimbus.api.service.EstadoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
@RequiredArgsConstructor
@Tag(name = "Estados", description = "Endpoints para gerenciamento de estados")
public class EstadoController {

    private final EstadoService estadoService;

    @GetMapping
    public ResponseEntity<List<Estado>> findAll() {
        return ResponseEntity.ok(estadoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> findById(@PathVariable Long id) {
        return ResponseEntity.ok(estadoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Estado> save(@RequestBody EstadoDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estadoService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> update(@RequestBody EstadoDto dto, @PathVariable Long id){
        return ResponseEntity.ok(estadoService.update(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        estadoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

