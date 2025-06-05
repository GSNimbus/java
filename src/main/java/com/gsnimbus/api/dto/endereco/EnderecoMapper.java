package com.gsnimbus.api.dto.endereco;

import com.gsnimbus.api.model.Bairro;
import com.gsnimbus.api.model.Cidade;
import com.gsnimbus.api.model.Endereco;
import com.gsnimbus.api.model.Localizacao;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
    @Mapping(target = "idBairro.id", source = "bairro")
    @Mapping(target = "nrLogradouro", source = "numLogradouro")
    @Mapping(target = "nmLogradouro", source = "logradouro")
    @Mapping(target = "idEndereco", ignore = true)
    Endereco toEntity(EnderecoDto dto);

    @Mapping(target = "numLogradouro", source = "nrLogradouro")
    @Mapping(target = "logradouro", source = "nmLogradouro")
    @Mapping(target = "bairro", source = "idBairro.id")
    EnderecoDto toDto(Endereco entity);

    @Mapping(target = "logradouro", source = "nomeLogradouro")
    EnderecoDto toDto(NovoEnderecoDto dto);

    @Mapping(target = "nrLogradouro", source = "numLogradouro")
    @Mapping(target = "nmLogradouro", source = "logradouro")
    @Mapping(target = "idBairro.id", source = "bairro")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(EnderecoDto dto, @MappingTarget Endereco entity);




}
