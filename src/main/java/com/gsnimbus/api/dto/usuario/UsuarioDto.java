package com.gsnimbus.api.dto.usuario;

import lombok.Data;

@Data
public class UsuarioDto {
    private String username;
    private String password;
    private String email;
}

