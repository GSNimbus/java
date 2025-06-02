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
    @Mapping(source = "idLocalizacao", target = "idLocalizacao.id")
    Alerta toEntity(AlertaDTO dto);

    @Mapping(source = "idLocalizacao.id", target = "idLocalizacao")
    AlertaDTO toDto(Alerta entity);

    @Mapping(target = "horarioAlerta", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "idLocalizacao", target = "idLocalizacao.id")
    void updateEntityFromDto(AlertaDTO dto, @MappingTarget Alerta entity);

    default Localizacao mapLongToLocalizacao(Long id) {
        if (id == null) return null;
        Localizacao localizacao = new Localizacao();
        localizacao.setId(id);
        return localizacao;
    }

    default Long mapLocalizacaoToLong(Localizacao localizacao) {
        return localizacao != null ? localizacao.getId() : null;
    }
}

