package com.gsnimbus.api.controller;

import com.gsnimbus.api.dto.endereco.EnderecoDto;
import com.gsnimbus.api.dto.endereco.NovoEnderecoDto;
import com.gsnimbus.api.model.Endereco;
import com.gsnimbus.api.service.EnderecoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/endereco")
@RequiredArgsConstructor
@Tag(name = "Endereços", description = "Endpoints para gerenciamento de endereços")
public class EnderecoController {
    private final EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<Endereco>> findAll(){
        return ResponseEntity.ok(enderecoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> findById(@PathVariable Long id){
        return ResponseEntity.ok(enderecoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Endereco> save(@RequestBody EnderecoDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.save(dto));
    }

    @PostMapping("/todo")
    public ResponseEntity<Endereco> save(@RequestBody NovoEnderecoDto dto){
        Endereco endereco = enderecoService.saveFull(dto);
        log.info("Endereço: {}", endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> update(@RequestBody EnderecoDto dto, @PathVariable Long id){
        return ResponseEntity.ok(enderecoService.update(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        enderecoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
