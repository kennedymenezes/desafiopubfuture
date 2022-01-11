package br.com.desafiopubfuture.dto;

import br.com.desafiopubfuture.enums.TipoReceita;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class ReceitaDto {

    @NotNull
    private Integer contaId;

    @NotNull
    private TipoReceita tipoReceita;

    @NotNull
    private BigDecimal valor;

    private Date dataRecebimento;
    private Date dataRecebimentoEsperado;

    private String descricao;
}
