package com.gsnimbus.api.repository;

import com.gsnimbus.api.model.Alerta;
import com.gsnimbus.api.model.AlertaBairro;
import com.gsnimbus.api.model.Bairro;
import com.gsnimbus.api.model.Previsao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AlertaBairroRepository extends JpaRepository<AlertaBairro, Long> {
    @Query(value = "SELECT ab.alerta FROM AlertaBairro ab WHERE ab.bairro.id = :idBairro ORDER BY ab.alerta.id DESC LIMIT 1")
    Alerta findLastAlertaByBairro (@Param("idBairro") Long idBairro);

    @Query("SELECT ab.alerta FROM AlertaBairro ab WHERE ab.bairro = :bairro")
    List<Alerta> findAllAlertaByBairro(@Param("bairro") Bairro bairro);

    @Query("SELECT ab.alerta FROM AlertaBairro ab WHERE ab.bairro.id = :idBairro " +
           "AND ab.alerta.horarioAlerta >= :startOfDay AND ab.alerta.horarioAlerta <= :endOfDay " +
           "ORDER BY ab.alerta.horarioAlerta DESC")
    List<Alerta> findAllAlertaByBairroAndDate(@Param("idBairro") Long idBairro,
                                             @Param("startOfDay") LocalDateTime startOfDay,
                                             @Param("endOfDay") LocalDateTime endOfDay);
}
