package com.gsnimbus.api.controller;

import com.gsnimbus.api.dto.previsao.api.PrevisaoDTO;
import com.gsnimbus.api.model.Previsao;
import com.gsnimbus.api.service.PrevisaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/previsao")
@RequiredArgsConstructor
@Tag(name = "Previsões", description = "Endpoints para gerenciamento de previsões")
public class PrevisaoController {

    private final PrevisaoService previsaoService;


    @GetMapping
    public ResponseEntity<List<Previsao>> findAll(){
        return ResponseEntity.ok(previsaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Previsao> findById(@PathVariable Long id){
        return ResponseEntity.ok(previsaoService.findById(id));
    }

    @GetMapping("/bairro/{idBairro}")
    public ResponseEntity<Previsao> findLastPrevisaoPorBairro(@PathVariable Long idBairro) {
        return ResponseEntity.ok(previsaoService.findLastPrevisaoByBairro(idBairro));
    }
    @PostMapping
    public ResponseEntity<Previsao> save(@RequestBody PrevisaoDTO previsaoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(previsaoService.save(previsaoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Previsao> update(@RequestBody PrevisaoDTO previsaoDTO, @PathVariable Long id){
        return ResponseEntity.ok(previsaoService.update(previsaoDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        previsaoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}
