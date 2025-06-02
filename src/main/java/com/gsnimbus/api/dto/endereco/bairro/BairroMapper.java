package com.gsnimbus.api.dto.endereco.bairro;

import com.gsnimbus.api.model.Bairro;
import com.gsnimbus.api.model.Cidade;
import com.gsnimbus.api.model.Localizacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BairroMapper {
    @Mapping(target = "id", ignore = true)
    Bairro toEntity(BairroDto dto);

    @Mapping(target = "nome", source = "nome")
    BairroDto toDto(Bairro entity);


    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(BairroDto dto, @MappingTarget Bairro bairro);


    default Cidade mapIdToCidade(Long id) {
        if (id == null) {
            return null;
        }
        Cidade cidade = new Cidade();
        cidade.setIdCidade(id);
        return cidade;
    }

    default Long mapCidadeToId(Cidade cidade) {
        return cidade != null ? cidade.getIdCidade() : null;
    }

    default Localizacao mapIdToLocalizacao(Long id) {
        if (id == null) {
            return null;
        }
        Localizacao localizacao = new Localizacao();
        localizacao.setId(id);
        return localizacao;
    }

    default Long mapLocalizacaoToId(Localizacao localizacao) {
        return localizacao != null ? localizacao.getId() : null;
    }
}
