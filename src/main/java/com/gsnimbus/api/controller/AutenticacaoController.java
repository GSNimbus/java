package com.gsnimbus.api.controller;

import com.gsnimbus.api.dto.usuario.LoginDto;
import com.gsnimbus.api.security.JWTUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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

    @PostMapping("/login")
    public String generateValidToken(@RequestBody LoginDto loginDto){
        try {
            String email = loginDto.getEmail();
            String password = loginDto.getPassword();
            var auth = new UsernamePasswordAuthenticationToken(email, password);
            log.info("Email recebido: {}", email);
            authenticationManager.authenticate(auth);
            return jwtUtil.buildToken(email);
        } catch (Exception e) {
            return "Usuário ou senha inválidos";
        }
    }

}
