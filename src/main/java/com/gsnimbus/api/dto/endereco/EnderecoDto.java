package com.gsnimbus.api.dto.endereco;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnderecoDto {
    String logradouro;
    Integer numLogradouro;
    Long bairro;
    String cep;
}
