package com.gsnimbus.api.dto.endereco;

public record EnderecoDto  (String logradouro,
                            Integer numLogradouro,
                            Long bairro,
                            String cep
                            ) {
}
