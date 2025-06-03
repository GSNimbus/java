package com.gsnimbus.api.repository;

import com.gsnimbus.api.model.Previsao;
import com.gsnimbus.api.model.PrevisaoBairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrevisaoBairroRepository extends JpaRepository<PrevisaoBairro, Long> {
    @Query(value = "SELECT pb.previsao FROM PrevisaoBairro pb WHERE pb.bairro.id = :idBairro ORDER BY pb.previsao.id DESC LIMIT 1")
    Previsao findLastPrevisaoByBairro (@Param("idBairro") Long idBairro);
}
