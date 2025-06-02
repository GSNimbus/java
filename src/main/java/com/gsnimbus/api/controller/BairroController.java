package com.gsnimbus.api.controller;

import com.gsnimbus.api.dto.endereco.bairro.BairroDto;
import com.gsnimbus.api.model.Bairro;
import com.gsnimbus.api.service.BairroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bairros")
@RequiredArgsConstructor
@Tag(name = "Bairros", description = "Endpoints para gerenciamento de bairros")
public class BairroController {

    private final BairroService bairroService;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(bairroService.save(dto));
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

