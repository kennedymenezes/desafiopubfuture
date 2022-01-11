package br.com.desafiopubfuture.repository;

import br.com.desafiopubfuture.enums.TipoDespesa;
import br.com.desafiopubfuture.model.Despesa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;

@Repository
public interface DespesaRepository extends CrudRepository<Despesa, Long> {
    Despesa findById(Integer id);

    Iterable<Despesa> findByTipoDespesa(TipoDespesa tipoDespesa);

    @Query(value = "SELECT * FROM Despesa  WHERE dataPagamento BETWEEN :inicio AND :fim", nativeQuery = true)
    Iterable<Despesa> findByDataPagamentoBetween(@Param("inicio") Date inicio, @Param("fim") Date fim);

    @Query(value = "SELECT SUM(valor) FROM Despesa", nativeQuery = true)
    BigDecimal totalDespesas();

    Iterable<Despesa> findByDescricaoContains(String descricao);

}
