package br.com.desafiopubfuture.dto;

import br.com.desafiopubfuture.enums.TipoConta;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ContaDto {
    @NotNull
    private Integer pessoaId;
    @NotNull
    private BigDecimal saldo;
    @NotNull
    private TipoConta tipoConta;

    private String numeroInstituicaoFinanceira;
    private String nomeInstituicaoFinanceira;
    private String agencia;
    private String contaCorrente;
}
