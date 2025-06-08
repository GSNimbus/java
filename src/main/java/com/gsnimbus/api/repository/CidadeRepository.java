package com.gsnimbus.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gsnimbus.api.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    @Query(value = "select c from Cidade c where c.nmCidade = :nome and c.idEstado.nmEstado = :estado")
    Optional<Cidade> findByName(String nome, String estado);

    Optional<Cidade> findFirstByNmCidade(String nome);
}
