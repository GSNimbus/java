package com.gsnimbus.api.dto.usuario;

import com.gsnimbus.api.model.Endereco;
import com.gsnimbus.api.model.Usuario;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "endereco", source = "idEndereco")
    @Mapping(target = "id", ignore = true)
    Usuario toEntity(UsuarioDto dto);

    @Mapping(target = "idEndereco", source = "endereco")
    UsuarioDto toDto(Usuario entity);

    @Mapping(target = "endereco", source = "idEndereco")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UsuarioDto dto, @MappingTarget Usuario entity);

    default Endereco mapIdToEndereco(Long id){
        if(id == null) return null;
        Endereco endereco = new Endereco();
        endereco.setIdEndereco(id);
        return endereco;
    }

    default Long mapEnderecoToId(Endereco endereco){
        return endereco != null ? endereco.getIdEndereco() : null;
    }

}

