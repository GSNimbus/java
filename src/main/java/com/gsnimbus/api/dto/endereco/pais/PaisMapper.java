package com.gsnimbus.api.dto.endereco.pais;

import com.gsnimbus.api.model.Pais;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PaisMapper {
    @Mapping(target = "idPais", ignore = true)
    @Mapping(target = "nmPais", source = "nmPais")
    Pais toEntity(PaisDto dto);

    @Mapping(target = "nmPais", source = "nmPais")
    PaisDto toDto(Pais entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(PaisDto paisDto, @MappingTarget Pais pais);
}
