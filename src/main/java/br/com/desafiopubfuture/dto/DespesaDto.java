package br.com.desafiopubfuture.dto;

import br.com.desafiopubfuture.enums.TipoDespesa;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class DespesaDto {

    @NotNull
    private Long contaId;

    @NotNull
    private TipoDespesa tipoDespesa;

    @NotNull
    private BigDecimal valor;

    private BigDecimal valorPago;

    private LocalDate dataPagamento;
    private LocalDate dataPagamentoEsperado;

    private String descricao;
}
