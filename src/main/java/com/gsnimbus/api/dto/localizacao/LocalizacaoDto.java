package com.gsnimbus.api.dto.localizacao;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocalizacaoDto {
    private BigDecimal longitude;
    private BigDecimal latitude;
}

