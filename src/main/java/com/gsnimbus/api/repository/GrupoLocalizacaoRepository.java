package com.gsnimbus.api.repository;

import com.gsnimbus.api.model.GrupoLocalizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoLocalizacaoRepository extends JpaRepository<GrupoLocalizacao, Long> {
    List<GrupoLocalizacao> findAllByUsuario_Id(Long usuarioId);
}
