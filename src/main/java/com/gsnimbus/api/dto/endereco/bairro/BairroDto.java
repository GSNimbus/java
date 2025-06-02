package com.gsnimbus.api.dto.endereco.bairro;

public record BairroDto(
    String nome,
    Long idCidade,
    Long idLocalizacao
) {}
