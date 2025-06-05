package com.gsnimbus.api.controller;

import com.gsnimbus.api.security.JWTUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Endpoints de gerenciamento de autenticação")
public class AutenticacaoController {
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String generateValidToken(@RequestParam String username, @RequestParam String password){
        try {
            var auth = new UsernamePasswordAuthenticationToken(username, password);
            authenticationManager.authenticate(auth);
            return jwtUtil.buildToken(username);
        } catch (Exception e) {
            return "Usuário ou senha inválidos";
        }
    }

}
