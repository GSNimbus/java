package com.gsnimbus.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gsnimbus.api.model.Bairro;
import com.gsnimbus.api.model.Localizacao;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long> {
    Optional<Bairro> findByNomeIgnoreCase(String nomeBairro);

    // Busca um bairro pela sua localização
    Bairro findByIdLocalizacao(Localizacao localizacao);

    Optional<Bairro> findByNomeIgnoreCaseAndIdCidade_IdCidade(String nomeBairro, Long idCidade);
}
