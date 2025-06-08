package com.gsnimbus.api.dto.localizacao.grupo;

import com.gsnimbus.api.model.Endereco;

import lombok.Data;

@Data
public class CasaGrupoProjection {
    private Endereco endereco;
    private String nome;
    private Long idGrupoLocalizacao;
}
