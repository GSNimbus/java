package com.gsnimbus.api.dto.endereco;

import com.gsnimbus.api.model.Cidade;
import com.gsnimbus.api.model.Endereco;
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

    default Cidade mapIdToCidade(Long id){
        if (id == null) return null;
        Cidade cidade = new Cidade();
        cidade.setIdCidade(id);
        return cidade;
    }

    default Long mapCidadeToId(Cidade cidade){
        return cidade != null ? cidade.getIdCidade() : null;
    }

}
