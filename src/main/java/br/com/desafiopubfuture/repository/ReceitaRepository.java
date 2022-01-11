package br.com.desafiopubfuture.repository;

import br.com.desafiopubfuture.enums.TipoReceita;
import br.com.desafiopubfuture.model.Receita;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;

@Repository
public interface ReceitaRepository extends CrudRepository<Receita, Long> {
    Receita findById(Integer id);

    @Query(value = "SELECT * FROM Receita  WHERE dataRecebimento BETWEEN :dataPagamentoInicial AND :dataPagamentoFinal", nativeQuery = true)
    Iterable<Receita> findByDataRecebimentoBetween(@Param("dataPagamentoInicial") Date dataRecebimentoInicial, @Param("dataPagamentoFinal") Date dataRecebimentoFinal);

    @Query(value = "SELECT SUM(valor) FROM Receita")
    BigDecimal totalReceitas();

    Iterable<Receita> findByDescricaoContains(String descricao);

    Iterable<Receita> findByTipoReceita(TipoReceita tipoReceita);
}
