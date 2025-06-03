package com.gsnimbus.api.repository;

import com.gsnimbus.api.model.Previsao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrevisaoRepository extends JpaRepository<Previsao, Long> {
}
