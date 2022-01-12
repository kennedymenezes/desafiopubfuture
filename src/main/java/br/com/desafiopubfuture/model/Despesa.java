package br.com.desafiopubfuture.model;

import br.com.desafiopubfuture.enums.TipoDespesa;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity

@Getter
@Setter
public class Despesa implements Serializable {
    private static final long serialVersionUID = 1L;

    //ID da despesa
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Data de cadastro e atualizacao do registro
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataCadastro;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataAtualizacao;

    //Conta da vinculada da despesa
    @ManyToOne
    private Conta conta;

    //Classificacao do tipo de despesa
    private TipoDespesa tipoDespesa;

    //Valor no tipo moeda para salvar o saldo
    private BigDecimal valor;

    //Data de previsao de pagamento e data da efetivacao do pagamento
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataPagamentoEsperado;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataPagamento;

    //Descricao da despesa
    private String descricao;

}
