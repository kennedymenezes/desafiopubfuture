package br.com.desafiopubfuture.dto;

import br.com.desafiopubfuture.enums.TipoReceita;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ReceitaDto {

    @NotNull
    private Long contaId;

    @NotNull
    private TipoReceita tipoReceita;

    @NotNull
    private BigDecimal valor;

    private BigDecimal valorRecebido;

    private LocalDate dataRecebimento;
    private LocalDate dataRecebimentoEsperado;

    private String descricao;
}
