package com.gsnimbus.api.dto.endereco.estado;

import com.gsnimbus.api.model.Estado;
import com.gsnimbus.api.model.Pais;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EstadoMapper {
    @Mapping(target = "nmEstado", source = "nome")
    @Mapping(target = "idEstado", ignore = true)
    Estado toEntity(EstadoDto dto);
    @Mapping(target = "nome", source = "nmEstado")
    EstadoDto toDto(Estado estado);

    default Pais mapIdToPais(Long id){
        if (id == null) return null;
        Pais pais = new Pais();
        pais.setIdPais(id);
        return pais;
    }

    default Long mapPaisToId(Pais pais){
        return pais != null ? pais.getIdPais() : null;
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(EstadoDto estadoDto, @MappingTarget Estado estado);
}
