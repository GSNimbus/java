package com.gsnimbus.api.dto.endereco;

import lombok.Data;

@Data
public class NovoEnderecoDto {
    private Integer numLogradouro;
    private String nomeLogradouro;
    private String cep;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;

    @Override
    public String toString() {
        return String.join(" ", nomeLogradouro, String.valueOf(numLogradouro), bairro, cidade, estado, pais, String.valueOf(cep));
    }
}
