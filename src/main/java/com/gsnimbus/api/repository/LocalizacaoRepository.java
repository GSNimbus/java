package com.gsnimbus.api.repository;

import com.gsnimbus.api.model.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {
    @Query(value = "select l from Localizacao l where round(l.latitude, 3) = round(:latitude, 3) and round(l.longitude, 3) = round(:longitude, 3)")
    Optional<Localizacao> findByLocalizacao(@Param("latitude") BigDecimal latitude, @Param("longitude") BigDecimal longitude);
}

