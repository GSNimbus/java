package com.gsnimbus.api.service.events;

import com.gsnimbus.api.model.Bairro;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BairroCriadoEvent {
    private final Bairro bairro;
}
