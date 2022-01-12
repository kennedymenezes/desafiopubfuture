package br.com.desafiopubfuture.repository;

import br.com.desafiopubfuture.enums.TipoReceita;
import br.com.desafiopubfuture.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    @Query(value = "SELECT * FROM receita  WHERE data_recebimento BETWEEN :? AND :? ORDER BY data_recebimento DESC", nativeQuery = true)
    Iterable<Receita> findByDataRecebimentoBetween(LocalDate dataRecebimentoInicial, LocalDate dataRecebimentoFinal);

    @Query(value = "SELECT SUM(valor) FROM Receita")
    BigDecimal totalReceitas();

    Iterable<Receita> findByDescricaoContains(String descricao);

    Iterable<Receita> findByTipoReceita(TipoReceita tipoReceita);
}
