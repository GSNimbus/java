package com.gsnimbus.api.dto.alerta;

import com.gsnimbus.api.model.Alerta;
import com.gsnimbus.api.model.Localizacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.BeanMapping;

@Mapper(componentModel = "spring")
public interface AlertaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "horarioAlerta", ignore = true)
    Alerta toEntity(AlertaDTO dto);

    AlertaDTO toDto(Alerta entity);

    @Mapping(target = "horarioAlerta", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(AlertaDTO dto, @MappingTarget Alerta entity);

}

