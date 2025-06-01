package com.gsnimbus.api.controller;

import com.gsnimbus.api.dto.endereco.pais.PaisDto;
import com.gsnimbus.api.model.Pais;
import com.gsnimbus.api.service.PaisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pais")
@RequiredArgsConstructor
public class PaisController {

    private final PaisService paisService;

    @GetMapping
    public ResponseEntity<List<Pais>> findAll() {
        return ResponseEntity.ok(paisService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pais> findById(@PathVariable Long id) {
        return ResponseEntity.ok(paisService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Pais> save(@RequestBody PaisDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paisService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pais> update(@RequestBody PaisDto dto, @PathVariable Long id){
        return ResponseEntity.ok(paisService.update(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        paisService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
