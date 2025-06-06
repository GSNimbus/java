package com.gsnimbus.api.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoggedDto {
    private String token;
    private Long idUsuario;
}
