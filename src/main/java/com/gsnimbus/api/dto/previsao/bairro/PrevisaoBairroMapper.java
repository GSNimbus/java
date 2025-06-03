package com.gsnimbus.api.dto.previsao.bairro;

import com.gsnimbus.api.model.Bairro;
import com.gsnimbus.api.model.Previsao;
import com.gsnimbus.api.model.PrevisaoBairro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PrevisaoBairroMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "previsao", source = "idPrevisao")
    @Mapping(target = "bairro", source = "idBairro")
    PrevisaoBairro toEntity(PrevisaoBairroDTO dto);


    @Mapping(target = "idPrevisao", source = "previsao")
    @Mapping(target = "idBairro", source = "bairro")
    PrevisaoBairroDTO toDto(PrevisaoBairro entity);

    default Long mapPrevisaoToId(Previsao previsao){
        return previsao != null ? previsao.getId() : null;
    }

    default Previsao mapIdToPrevisao(Long id){
        if (id == null) return null;
        Previsao previsao = new Previsao();
        previsao.setId(id);
        return previsao;
    }

    default Long mapBairroToId(Bairro bairro) {
        return bairro != null ? bairro.getId() : null;
    }

    default Bairro mapIdToBairro(Long id) {
        if (id == null) return null;
        Bairro bairro = new Bairro();
        bairro.setId(id);
        return bairro;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "previsao", source = "idPrevisao")
    @Mapping(target = "bairro", source = "idBairro")
    void updateEntityFromDto(PrevisaoBairroDTO dto, @MappingTarget PrevisaoBairro previsaoBairro);
}
