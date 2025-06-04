package com.gsnimbus.api.controller;

import com.gsnimbus.api.dto.alerta.AlertaDTO;
import com.gsnimbus.api.model.Alerta;
import com.gsnimbus.api.service.AlertaBairroService;
import com.gsnimbus.api.service.AlertaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerta")
@RequiredArgsConstructor
@Tag(name = "Alertas", description = "Endpoints para gerenciamento de alertas")
public class AlertaController {

    private final AlertaService alertaService;
    private final AlertaBairroService alertaBairroService;
    @GetMapping
    public ResponseEntity<List<Alerta>> findAll() {
        return ResponseEntity.ok(alertaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alerta> findById(@PathVariable Long id) {
        return ResponseEntity.ok(alertaService.findById(id));
    }

    @GetMapping("/bairro/{idBairro}")
    public ResponseEntity<List<Alerta>> findAllAlertaByBairro(@PathVariable Long idBairro){
        return ResponseEntity.ok(alertaBairroService.findAllAlertaByBairro(idBairro));
    }

    @GetMapping("/bairro/{idBairro}/hoje")
    public ResponseEntity<List<Alerta>> findAllAlertaByBairroToday(@PathVariable Long idBairro){
        return ResponseEntity.ok(alertaBairroService.findAllAlertaByBairroToday(idBairro));
    }


    @PostMapping
    public ResponseEntity<Alerta> save(@RequestBody AlertaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alertaService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alerta> update(@RequestBody AlertaDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(alertaService.update(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        alertaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

