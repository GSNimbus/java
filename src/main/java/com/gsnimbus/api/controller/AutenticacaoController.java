package com.gsnimbus.api.controller;

import com.gsnimbus.api.dto.usuario.LoginDto;
import com.gsnimbus.api.dto.usuario.UserLoggedDto;
import com.gsnimbus.api.exception.UserNotAuthorizedException;
import com.gsnimbus.api.model.Usuario;
import com.gsnimbus.api.security.JWTUtil;
import com.gsnimbus.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autenticacao")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Endpoints de gerenciamento de autenticação")
@Log4j2
public class AutenticacaoController {
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<UserLoggedDto> generateValidToken(@RequestBody LoginDto loginDto){
        try {
            String email = loginDto.getEmail();
            String password = loginDto.getPassword();
            var auth = new UsernamePasswordAuthenticationToken(email, password);
            log.info("Email recebido: {}", email);
            authenticationManager.authenticate(auth);
            Usuario usuario = usuarioService.findByEmail(email);
            Long id = usuario.getId();
            return ResponseEntity.ok(new UserLoggedDto(jwtUtil.buildToken(email), id));
        } catch (Exception e) {
            throw new UserNotAuthorizedException("Usuário ou senha inválidos");
        }
    }

}
