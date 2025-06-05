package com.gsnimbus.api.repository;

import com.gsnimbus.api.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    @Query("SELECT a FROM Alerta a WHERE a.horarioAlerta >= :start AND a.horarioAlerta < :end AND (:idBairro IS NULL OR a.idBairro.id = :idBairro)")
    List<Alerta> findAllByHorarioAlertaToday(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("idBairro") Long idBairro);

    List<Alerta> findAllAlertaByIdBairroId(Long idBairro);
}

