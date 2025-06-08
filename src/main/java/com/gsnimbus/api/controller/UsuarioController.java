package com.gsnimbus.api.controller;

import com.gsnimbus.api.dto.usuario.UsuarioDto;
import com.gsnimbus.api.exception.UserAlreadyExistsException;
import com.gsnimbus.api.model.Usuario;
import com.gsnimbus.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Endpoints para gerenciamento de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;


    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody UsuarioDto dto) {
        Usuario usuario = usuarioService.findByEmail(dto.getEmail());
        if (usuario != null) {
            throw new UserAlreadyExistsException("O email " + dto.getEmail() + "já tem uma conta!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@RequestBody UsuarioDto dto, @PathVariable Long id){
        return ResponseEntity.ok(usuarioService.update(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

