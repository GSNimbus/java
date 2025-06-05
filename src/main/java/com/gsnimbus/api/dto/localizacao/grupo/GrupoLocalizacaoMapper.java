package com.gsnimbus.api.dto.localizacao.grupo;

import com.gsnimbus.api.model.Endereco;
import com.gsnimbus.api.model.GrupoLocalizacao;
import com.gsnimbus.api.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GrupoLocalizacaoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", source = "idUsuario")
    @Mapping(target = "endereco", source = "idEndereco")
    GrupoLocalizacao toEntity(GrupoLocalizacaoDto dto);
    @Mapping(target = "idUsuario", source = "usuario")
    @Mapping(target = "idEndereco", source = "endereco")
    GrupoLocalizacaoDto toDto(GrupoLocalizacao entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "endereco", source = "idEndereco")
    @Mapping(target = "usuario", source = "idUsuario")
    void updateEntityFromDto(GrupoLocalizacaoDto dto, @MappingTarget GrupoLocalizacao grupoLocalizacao);

    default Usuario mapIdToUsuario(Long id){
        if (id == null) return null;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return usuario;
    }

    default Long mapUsuarioToId(Usuario usuario){
        return usuario != null ? usuario.getId() : null;
    }

    default Endereco mapIdToEndereco(Long id){
        if (id == null) return null;
        Endereco endereco = new Endereco();
        endereco.setIdEndereco(id);
        return endereco;
    }

    default Long mapEnderecoToId(Endereco endereco){
        return endereco != null ? endereco.getIdEndereco() : null;
    }

}
