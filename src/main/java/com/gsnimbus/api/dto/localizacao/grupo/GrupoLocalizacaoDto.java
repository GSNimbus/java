package com.gsnimbus.api.dto.localizacao.grupo;

import lombok.Data;

@Data
public class GrupoLocalizacaoDto {
    private String nome;
    private Long idEndereco;
    private Long idUsuario;
}
