package com.gsnimbus.api.dto;

import java.math.BigDecimal;

public record LocalizacaoDTO(
    BigDecimal longitude,
    BigDecimal latitude
) {}
