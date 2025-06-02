package com.gsnimbus.api.dto.localizacao;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class LocalizacaoDto {
    private BigDecimal longitude;
    private BigDecimal latitude;
}

