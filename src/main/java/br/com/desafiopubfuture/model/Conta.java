package br.com.desafiopubfuture.model;

import br.com.desafiopubfuture.enums.TipoConta;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Conta implements Serializable {
    private static final long serialVersionUID = 1L;

    //ID da conta
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Define N(Contas) -> 1 (Pessoa)
    @ManyToOne
    private Pessoa pessoa;

    //Data de cadastro e atualizacao do registro
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAtualizacao;

    //Saldo atual da conta
    private BigDecimal saldo;

    //Tipo de conta
    private TipoConta tipoConta;

    //Número da instituicao financeira, nome, agência e conta corrente
    private String numeroInstituicaoFinanceira;
    private String nomeInstituicaoFinanceira;
    private String agencia;
    private String contaCorrente;

}
