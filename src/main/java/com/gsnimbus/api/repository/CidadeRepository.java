package com.gsnimbus.api.repository;

import com.gsnimbus.api.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    @Query(value = "select c from Cidade c where c.nmCidade = :nome and c.idEstado.nmEstado = :estado")
    Optional<Cidade> findByName(String nome, String estado);
}
