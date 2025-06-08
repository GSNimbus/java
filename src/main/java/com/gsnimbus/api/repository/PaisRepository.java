package com.gsnimbus.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gsnimbus.api.model.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {
    Optional<Pais> findFirstByNmPais(String nome);
}
