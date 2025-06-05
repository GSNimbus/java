package com.gsnimbus.api.dto.usuario;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
