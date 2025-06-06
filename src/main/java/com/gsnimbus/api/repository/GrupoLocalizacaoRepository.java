package com.gsnimbus.api.repository;

import com.gsnimbus.api.model.Endereco;
import com.gsnimbus.api.model.GrupoLocalizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoLocalizacaoRepository extends JpaRepository<GrupoLocalizacao, Long> {
    List<GrupoLocalizacao> findAllByUsuario_Id(Long usuarioId);

    @Query(value = "select gl.endereco from GrupoLocalizacao gl where gl.usuario.id = :idUsuario ORDER BY gl.usuario.id ASC LIMIT 1")
    Endereco findHomeUsuario(Long idUsuario);
}
