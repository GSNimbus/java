package com.gsnimbus.api.dto.endereco.bairro;

import com.gsnimbus.api.model.Bairro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BairroMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "idCidade.idCidade", source = "idCidade")
    @Mapping(target = "idLocalizacao.id", source = "idLocalizacao")
    Bairro toEntity(BairroDto dto);

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "idCidade", source = "idCidade.idCidade")
    @Mapping(target = "idLocalizacao", source = "idLocalizacao.id")
    BairroDto toDto(Bairro entity);


    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(BairroDto dto, @MappingTarget Bairro bairro);
}
