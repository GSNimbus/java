package com.gsnimbus.api.dto.previsao.api;

import com.gsnimbus.api.model.Previsao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PrevisaoMapper {

    // --- Métodos para mapear entre CurrentDto e a entidade Previsao ---
    // Estes são os mapeamentos base.
    // Adapte os nomes dos métodos se preferir (ex: os que você tinha no arquivo).

    /**
     * Mapeia CurrentDto para a entidade Previsao.
     * Adicione anotações @Mapping se os nomes dos campos não corresponderem.
     * Ex: @Mapping(target = "temperatura", source = "temperature_2m")
     */
    @Mapping(target = "id", ignore = true) // Ignora o ID ao criar uma nova entidade
    // Adicione aqui os @Mappings de CurrentDto para Previsao, se necessário:
    // @Mappings({
    //     @Mapping(target = "temperatura", source = "temperature_2m"),
    //     @Mapping(target = "precipitacao", source = "precipitation"),
    //     // ... outros campos
    // })
    Previsao mapCurrentDtoToEntity(CurrentDto currentDto);

    /**
     * Mapeia a entidade Previsao para CurrentDto.
     * Adicione anotações @Mapping se os nomes dos campos não corresponderem.
     * Ex: @Mapping(target = "temperature_2m", source = "temperatura")
     */
    // Adicione aqui os @Mappings de Previsao para CurrentDto, se necessário:
    // @Mappings({
    //     @Mapping(target = "temperature_2m", source = "temperatura"),
    //     @Mapping(target = "precipitation", source = "precipitacao"),
    //     // ... outros campos
    // })
    CurrentDto mapEntityToCurrentDto(Previsao previsao);


    // --- Métodos principais para conversão envolvendo PrevisaoDTO ---

    /**
     * Converte PrevisaoDTO para a entidade Previsao.
     * Usado pelo PrevisaoService.
     */
    default Previsao toEntity(PrevisaoDTO previsaoDTO) {
        if (previsaoDTO == null || previsaoDTO.currentDto() == null) {
            return null;
        }
        // Delega para o mapeador de CurrentDto para Previsao
        return mapCurrentDtoToEntity(previsaoDTO.currentDto());
    }

    /**
     * Converte a entidade Previsao para PrevisaoDTO.
     */
    default PrevisaoDTO toDto(Previsao previsao) {
        if (previsao == null) {
            return null;
        }
        // Delega para o mapeador de Previsao para CurrentDto
        CurrentDto currentDto = mapEntityToCurrentDto(previsao);
        return new PrevisaoDTO(currentDto);
    }

    /**
     * Atualiza uma entidade Previsao existente a partir de um PrevisaoDTO.
     * Usado pelo PrevisaoService.
     * O MapStruct tentará mapear campos de dto.currentDto() para a entidade 'previsao'.
     * Adicione @Mapping(target = "campoPrevisao", source = "dto.currentDto.campoCurrent")
     * se os nomes dos campos não forem diretamente compatíveis.
     */
    @Mapping(target = "id", ignore = true) // Geralmente não se atualiza o ID da entidade
    // Adicione aqui os @Mappings de PrevisaoDTO (via currentDto) para Previsao, se necessário:
    // @Mappings({
    //     @Mapping(target = "temperatura", source = "dto.currentDto.temperature_2m"),
    //     @Mapping(target = "precipitacao", source = "dto.currentDto.precipitation"),
    //     // ... outros campos
    // })
    void updateEntityFromDto(PrevisaoDTO dto, @MappingTarget Previsao previsao);
}
