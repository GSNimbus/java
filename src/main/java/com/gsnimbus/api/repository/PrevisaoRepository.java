package com.gsnimbus.api.repository;

import com.gsnimbus.api.model.Previsao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrevisaoRepository extends JpaRepository<Previsao, Long> {
    @Query(value = "SELECT pb FROM Previsao pb WHERE pb.idBairro.id = :idBairro ORDER BY pb.id DESC LIMIT 1")
    Previsao findLastPrevisaoByBairro (@Param("idBairro") Long idBairro);
}
