package com.gsnimbus.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gsnimbus.api.model.Bairro;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long> {
    Optional<Bairro> findFirstByNomeIgnoreCase(String nomeBairro);

}
