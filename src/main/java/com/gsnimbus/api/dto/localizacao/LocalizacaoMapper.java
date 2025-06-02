package com.gsnimbus.api.dto.localizacao;

import com.gsnimbus.api.model.Localizacao;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface LocalizacaoMapper {

    @Mapping(target = "id", ignore = true)
    Localizacao toEntity(LocalizacaoDto dto);

    LocalizacaoDto toDto(Localizacao entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(LocalizacaoDto dto, @MappingTarget Localizacao entity);
}

