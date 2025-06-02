package com.gsnimbus.api.dto.endereco.bairro;

import com.gsnimbus.api.model.Bairro;
import com.gsnimbus.api.model.Cidade;
import com.gsnimbus.api.model.Localizacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BairroMapper {
    @Mapping(target = "id", ignore = true)
    Bairro toEntity(BairroDto dto);

    @Mapping(target = "nome", source = "nome")
    BairroDto toDto(Bairro entity);

    default Cidade mapIdToCidade(Long idCidade) {
        if (idCidade == null) {
            return null;
        }
        Cidade cidade = new Cidade();
        cidade.setIdCidade(idCidade);
        return cidade;
    }

    default Long mapCidadeToId(Cidade cidade) {
        return cidade != null ? cidade.getIdCidade() : null;
    }

    default Localizacao mapIdToLocalizacao(Long idLocalizacao) {
        if (idLocalizacao == null) {
            return null;
        }
        Localizacao localizacao = new Localizacao();
        localizacao.setId(idLocalizacao);
        return localizacao;
    }

    default Long mapLocalizacaoToId(Localizacao localizacao) {
        return localizacao != null ? localizacao.getId() : null;
    }


    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(BairroDto dto, @MappingTarget Bairro bairro);
}
