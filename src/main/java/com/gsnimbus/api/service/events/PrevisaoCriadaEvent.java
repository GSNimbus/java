package com.gsnimbus.api.service.events;

import com.gsnimbus.api.dto.previsao.api.PrevisaoDTO;
import com.gsnimbus.api.model.Previsao;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PrevisaoCriadaEvent {
    private final Previsao previsao;
    private final Long idBairro;
}
