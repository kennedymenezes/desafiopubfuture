package br.com.desafiopubfuture.model;

import br.com.desafiopubfuture.enums.TipoReceita;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class Receita implements Serializable {
    private static final long serialVersionUID = 1L;

    //ID da receita
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Data de cadastro e atualizacao do registro
    private Date dataCadastro;
    private Date dataAtualizacao;

    //Conta da vinculada da despesa
    @ManyToOne
    private Conta conta;

    //Classificacao do tipo de receita
    private TipoReceita tipoReceita;

    //Valor no tipo moeda para salvar o saldo
    private BigDecimal valor;

    //Data de previsao de recebimento e data efetiva do recebimento
    @Temporal(TemporalType.DATE)
    private Date dataRecebimento;
    @Temporal(TemporalType.DATE)
    private Date dataRecebimentoEsperado;

    //Descricao da receita
    private String descricao;
}
