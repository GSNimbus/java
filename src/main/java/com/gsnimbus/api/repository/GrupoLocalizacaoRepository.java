package com.gsnimbus.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gsnimbus.api.dto.localizacao.grupo.CasaGrupoProjection;
import com.gsnimbus.api.model.GrupoLocalizacao;

@Repository
public interface GrupoLocalizacaoRepository extends JpaRepository<GrupoLocalizacao, Long> {
    List<GrupoLocalizacao> findAllByUsuario_Id(Long usuarioId);

    @Query("select gl.endereco as endereco, gl.id as idGrupoLocalizacao, gl.nome as nome from GrupoLocalizacao gl where gl.usuario.id = :idUsuario ORDER BY gl.id ASC")
    List<CasaGrupoProjection> findProjectionsByUsuarioIdOrderedById(Long idUsuario);
}
