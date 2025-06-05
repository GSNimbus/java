package com.gsnimbus.api.dto.localizacao;

import com.gsnimbus.api.model.Bairro;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocalizacaoNovaProjection {
    Bairro bairro;
}
