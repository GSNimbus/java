package com.gsnimbus.api.dto.endereco.estado;

import com.gsnimbus.api.model.Estado;
import com.gsnimbus.api.model.Pais;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EstadoMapper {
    @Mapping(target = "nmEstado", source = "nome")
    @Mapping(target = "idEstado", ignore = true)
    @Mapping(target = "idPais.idPais", source = "idPais")
    Estado toEntity(EstadoDto dto);
    @Mapping(target = "nome", source = "nmEstado")
    @Mapping(target = "idPais", source = "idPais.idPais")
    EstadoDto toDto(Estado estado);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(EstadoDto estadoDto, @MappingTarget Estado estado);
}
