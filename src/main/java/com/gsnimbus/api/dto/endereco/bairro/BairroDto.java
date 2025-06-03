package com.gsnimbus.api.dto.endereco.bairro;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BairroDto{
    String nome;
    Long idCidade;
    Long idLocalizacao;
}
