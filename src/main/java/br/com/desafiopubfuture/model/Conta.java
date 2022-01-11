package br.com.desafiopubfuture.model;

import br.com.desafiopubfuture.enums.TipoConta;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class Conta implements Serializable {
    private static final long serialVersionUID = 1L;

    //ID da conta
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //Define N(Contas) -> 1 (Pessoa)
    @ManyToOne
    private Pessoa pessoa;

    //Data de cadastro e atualizacao do registro
    private Date dataCadastro;
    private Date dataAtualizacao;

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
