package br.com.desafiopubfuture.repository;

import br.com.desafiopubfuture.enums.TipoDespesa;
import br.com.desafiopubfuture.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    List<Despesa> findByDescricaoContains(String descricao);

    List<Despesa> findByTipoDespesa(TipoDespesa tipoDespesa);

    @Query(value = "SELECT * FROM despesa  WHERE data_pagamento BETWEEN :? AND :? ORDER BY data_pagamento DESC", nativeQuery = true)
    List<Despesa> findByDataPagamentoBetween(LocalDate inicio, LocalDate fim);


    @Query(value = "SELECT SUM(valor) FROM Despesa", nativeQuery = true)
    BigDecimal totalDespesas();


}
