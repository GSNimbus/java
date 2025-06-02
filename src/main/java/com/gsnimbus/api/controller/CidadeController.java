package com.gsnimbus.api.controller;

import com.gsnimbus.api.dto.endereco.cidade.CidadeDto;
import com.gsnimbus.api.model.Cidade;
import com.gsnimbus.api.service.CidadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
@RequiredArgsConstructor
@Tag(name = "Cidades", description = "Endpoints para gerenciamento de cidades")
public class CidadeController {

    private final CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<Cidade>> findAll() {
        return ResponseEntity.ok(cidadeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cidadeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Cidade> save(@RequestBody CidadeDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cidade> update(@RequestBody CidadeDto dto, @PathVariable Long id){
        return ResponseEntity.ok(cidadeService.update(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        cidadeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

