package br.com.desafiopubfuture.model;

import br.com.desafiopubfuture.enums.TipoReceita;
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
public class Receita implements Serializable {
    private static final long serialVersionUID = 1L;

    //ID da receita
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Data de cadastro e atualizacao do registro
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAtualizacao;

    //Conta da vinculada da despesa
    @ManyToOne
    private Conta conta;

    //Classificacao do tipo de receita
    private TipoReceita tipoReceita;

    //Valor no tipo moeda para salvar o saldo
    private BigDecimal valor;
    private BigDecimal valorRecebido;

    //Data de previsao de recebimento e data efetiva do recebimento
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataRecebimento;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataRecebimentoEsperado;

    //Descricao da receita
    private String descricao;
}
