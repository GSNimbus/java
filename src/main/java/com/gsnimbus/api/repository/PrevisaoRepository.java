package com.gsnimbus.api.repository;

import com.gsnimbus.api.model.Alerta;
import com.gsnimbus.api.model.Previsao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PrevisaoRepository extends JpaRepository<Previsao, Long> {
    @Query(value = "SELECT pb FROM Previsao pb WHERE pb.idBairro.id = :idBairro ORDER BY pb.id DESC LIMIT 1")
    Previsao findLastPrevisaoByBairro (@Param("idBairro") Long idBairro);

    @Query("SELECT p FROM Previsao p " +
            "WHERE p.idBairro IN (SELECT gl.endereco.idBairro FROM GrupoLocalizacao gl WHERE gl.usuario.id = :idUsuario) " +
            "AND p.id IN (SELECT MAX(p2.id) FROM Previsao p2 GROUP BY p2.idBairro)")
    List<Previsao> findAllLastPrevisaoByUser(@Param("idUsuario") Long idUsuario);
}
