package com.gsnimbus.api.dto.localizacao;

import com.gsnimbus.api.model.Localizacao;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.BeanMapping;

@Mapper(componentModel = "spring")
public interface LocalizacaoMapper {

    Localizacao toEntity(LocalizacaoDto dto);

    LocalizacaoDto toDto(Localizacao entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(LocalizacaoDto dto, @MappingTarget Localizacao entity);
}

