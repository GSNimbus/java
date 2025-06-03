package com.gsnimbus.api.dto.alerta.bairro;

import com.gsnimbus.api.model.Alerta;
import com.gsnimbus.api.model.AlertaBairro;
import com.gsnimbus.api.model.Bairro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AlertaBairroMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bairro", source = "idAlerta")
    @Mapping(target = "alerta", source = "idAlerta")
    AlertaBairro toEntity(AlertaBairroDTO dto);

    @Mapping(target = "idBairro", source = "bairro")
    @Mapping(target = "idAlerta", source = "alerta")
    AlertaBairroDTO toDto(AlertaBairro entity);

    @Mapping(target = "bairro", source = "idBairro")
    @Mapping(target = "alerta", source = "idAlerta")
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(AlertaBairroDTO dto, @MappingTarget AlertaBairro alertaBairro);

    default Bairro mapIdToBairro(Long id){
        if (id == null) return null;
        Bairro bairro = new Bairro();
        bairro.setId(id);
        return bairro;
    }

    default Long mapBairroToId(Bairro bairro){
        return bairro != null ? bairro.getId() : null;
    }

    default Alerta mapIdToAlerta(Long id){
        if (id == null) return null;
        Alerta alerta = new Alerta();
        alerta.setId(id);
        return alerta;
    }

    default Long mapAlertaToId(Alerta alerta){
        return alerta != null ? alerta.getId() : null;
    }

}
