package com.gsnimbus.api.repository;

import gp.moto.challenge_api.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PaisRepository extends JpaRepository<Pais, Long> {
    @Query("select p from Pais p where p.nmPais = :nome")
    Optional<Pais> findByName(String nome);
}
