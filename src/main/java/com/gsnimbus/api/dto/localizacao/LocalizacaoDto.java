package com.gsnimbus.api.dto.localizacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class LocalizacaoDto {
    private BigDecimal longitude;
    private BigDecimal latitude;
}

