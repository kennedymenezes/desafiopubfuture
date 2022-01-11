package br.com.desafiopubfuture.dto;

import br.com.desafiopubfuture.enums.TipoDespesa;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class DespesaDto {

    @NotNull
    private Integer contaId;

    @NotNull
    private TipoDespesa tipoDespesa;

    @NotNull
    private BigDecimal valor;

    private Date dataPagamento;
    private Date dataPagamentoEsperado;

    private String descricao;
}