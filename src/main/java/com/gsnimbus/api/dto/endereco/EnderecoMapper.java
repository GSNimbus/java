package com.gsnimbus.api.dto.endereco;

import com.gsnimbus.api.model.Bairro;
import com.gsnimbus.api.model.Cidade;
import com.gsnimbus.api.model.Endereco;
import com.gsnimbus.api.model.Localizacao;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
    @Mapping(target = "idBairro", source = "bairro")
    @Mapping(target = "nrLogradouro", source = "numLogradouro")
    @Mapping(target = "nmLogradouro", source = "logradouro")
    @Mapping(target = "idEndereco", ignore = true)
    Endereco toEntity(EnderecoDto dto);

    @Mapping(target = "numLogradouro", source = "nrLogradouro")
    @Mapping(target = "logradouro", source = "nmLogradouro")
    @Mapping(target = "bairro", source = "idBairro")
    EnderecoDto toDto(Endereco entity);

    @Mapping(target = "nrLogradouro", source = "numLogradouro")
    @Mapping(target = "nmLogradouro", source = "logradouro")
    @Mapping(target = "idBairro", source = "bairro")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(EnderecoDto dto, @MappingTarget Endereco entity);



    default Bairro mapIdToBairro(Long id) {
        if (id == null) return null;
        Bairro bairro = new Bairro();
        bairro.setId(id);
        return bairro;
    }

    default Long mapBairroToId(Bairro bairro) {
        return bairro != null ? bairro.getId() : null;
    }

    default Localizacao mapIdToLocalizacao(Long id) {
        if (id == null) return null;
        Localizacao localizacao = new Localizacao();
        localizacao.setId(id);
        return localizacao;
    }

    default Long mapLocalizacaoToId(Localizacao localizacao) {
        return localizacao != null ? localizacao.getId() : null;
    }

}
