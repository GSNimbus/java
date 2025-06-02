package com.gsnimbus.api.dto.endereco.cidade;

import com.gsnimbus.api.model.Cidade;
import com.gsnimbus.api.model.Estado;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CidadeMapper {
    @Mapping(target = "idCidade", ignore = true)
    @Mapping(target = "nmCidade", source = "nome")
    Cidade toEntity(CidadeDto dto);

    @Mapping(target = "nome", source = "nmCidade")
    CidadeDto toDto(Cidade entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(CidadeDto dto, @MappingTarget Cidade cidade);

    default Estado mapIdToEstado(Long id) {
        if (id == null) {
            return null;
        }
        Estado estado = new Estado();
        estado.setIdEstado(id);
        return estado;
    }

    default Long mapEstadoToId(Estado estado) {
        return estado != null ? estado.getIdEstado() : null;
    }
}
