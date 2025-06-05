package com.gsnimbus.api.dto.previsao.api;

import com.gsnimbus.api.model.Previsao;
import com.gsnimbus.api.model.Bairro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PrevisaoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "apparentTemperature", source = "apparentTemperature")
    @Mapping(target = "surfacePressure", source = "surfacePressure")
    @Mapping(target = "idBairro", ignore = true)
    Previsao mapCurrentDtoToEntity(CurrentDto currentDto);

    @Mapping(target = "apparentTemperature", source = "apparentTemperature")
    @Mapping(target = "surfacePressure", source = "surfacePressure")
    CurrentDto mapEntityToCurrentDto(Previsao previsao);

    default Previsao toEntity(PrevisaoDTO previsaoDTO) {
        if (previsaoDTO == null || previsaoDTO.getCurrentDto() == null) {
            return null;
        }
        Previsao previsao = mapCurrentDtoToEntity(previsaoDTO.getCurrentDto());

        if (previsaoDTO.getIdBairro() != null) {
            previsao.setIdBairro(mapIdToBairro(previsaoDTO.getIdBairro()));
        }

        return previsao;
    }

    default PrevisaoDTO toDto(Previsao previsao) {
        if (previsao == null) {
            return null;
        }
        CurrentDto currentDto = mapEntityToCurrentDto(previsao);
        PrevisaoDTO previsaoDTO = new PrevisaoDTO();
        previsaoDTO.setCurrentDto(currentDto);

        previsaoDTO.setIdBairro(mapBairroToId(previsao.getIdBairro()));

        return previsaoDTO;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "apparentTemperature", source = "currentDto.apparentTemperature")
    @Mapping(target = "surfacePressure", source = "currentDto.surfacePressure")
    @Mapping(target = "idBairro", ignore = true)
    void updateEntityFromDto(PrevisaoDTO dto, @MappingTarget Previsao previsao);

    default void afterUpdateEntityFromDto(PrevisaoDTO dto, @MappingTarget Previsao previsao) {
        if (dto.getIdBairro() != null) {
            previsao.setIdBairro(mapIdToBairro(dto.getIdBairro()));
        }
    }

    default Bairro mapIdToBairro(Long idBairro) {
        if (idBairro == null) {
            return null;
        }
        Bairro bairro = new Bairro();
        bairro.setId(idBairro);
        return bairro;
    }

    default Long mapBairroToId(Bairro bairro) {
        return bairro != null ? bairro.getId() : null;
    }
}