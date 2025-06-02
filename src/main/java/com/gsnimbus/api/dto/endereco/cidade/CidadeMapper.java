package com.gsnimbus.api.dto.endereco.cidade;

import com.gsnimbus.api.model.Cidade;
import com.gsnimbus.api.model.Estado;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CidadeMapper {
    @Mapping(target = "idCidade", ignore = true)
    @Mapping(target = "nmCidade", source = "nome")
    @Mapping(target = "idEstado.idEstado", source = "idEstado")
    Cidade toEntity(CidadeDto dto);

    @Mapping(target = "nome", source = "nmCidade")
    @Mapping(target = "idEstado", source = "idEstado.idEstado")
    CidadeDto toDto(Cidade entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(CidadeDto dto, @MappingTarget Cidade cidade);
}
