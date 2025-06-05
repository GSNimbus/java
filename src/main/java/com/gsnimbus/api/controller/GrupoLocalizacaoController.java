package com.gsnimbus.api.controller;

import com.gsnimbus.api.dto.localizacao.grupo.GrupoLocalizacaoDto;
import com.gsnimbus.api.model.GrupoLocalizacao;
import com.gsnimbus.api.service.GrupoLocalizacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupo-localizacao")
@RequiredArgsConstructor
@Tag(name = "Grupos de localização", description = "Endpoints de gerenciamento de localizações")
public class GrupoLocalizacaoController {

    private final GrupoLocalizacaoService grupoLocalizacaoService;

    @GetMapping
    public ResponseEntity<List<GrupoLocalizacao>> findAll(){
        return ResponseEntity.ok(grupoLocalizacaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrupoLocalizacao> findById(@PathVariable Long id){
        return ResponseEntity.ok(grupoLocalizacaoService.findById(id));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<GrupoLocalizacao>> findAllByUsuario(@PathVariable Long idUsuario){
        return ResponseEntity.ok(grupoLocalizacaoService.findAllByUsuario(idUsuario));
    }

    @PostMapping
    public ResponseEntity<GrupoLocalizacao> save(@RequestBody GrupoLocalizacaoDto grupoLocalizacaoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(grupoLocalizacaoService.save(grupoLocalizacaoDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GrupoLocalizacao> update(@RequestBody GrupoLocalizacaoDto dto, @PathVariable Long id){
        return ResponseEntity.ok(grupoLocalizacaoService.update(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        grupoLocalizacaoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
