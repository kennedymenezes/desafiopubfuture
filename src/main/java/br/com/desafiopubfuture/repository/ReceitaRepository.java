package br.com.desafiopubfuture.repository;

import br.com.desafiopubfuture.enums.TipoReceita;
import br.com.desafiopubfuture.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    @Query(value = "SELECT * FROM receita  WHERE data_recebimento BETWEEN :? AND :? ORDER BY data_recebimento DESC", nativeQuery = true)
    List<Receita> findByDataRecebimentoBetween(LocalDate dataRecebimentoInicial, LocalDate dataRecebimentoFinal);

    @Query(value = "SELECT SUM(valor) FROM Receita")
    BigDecimal totalReceitas();

    List<Receita> findByDescricaoContains(String descricao);

    List<Receita> findByTipoReceita(TipoReceita tipoReceita);
}
