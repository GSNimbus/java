package com.gsnimbus.api.dto.previsao.api;

import com.gsnimbus.api.model.Previsao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PrevisaoMapper {
    @Mapping(target = "id", ignore = true)
    Previsao toEntity(CurrentDto previsaoDTO);
    CurrentDto toDto(Previsao previsao);

    void updateEntityFromDto(PrevisaoDTO dto, @MappingTarget Previsao previsao);
}
