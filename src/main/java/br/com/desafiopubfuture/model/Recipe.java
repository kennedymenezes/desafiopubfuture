package br.com.desafiopubfuture.model;

import br.com.desafiopubfuture.enums.RecipeType;
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
public class Recipe implements Serializable {
    private static final long serialVersionUID = 1L;

    //ID da receita
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Data de cadastro e atualizacao do registro
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate registrationDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate updateDate;

    //Conta da vinculada da despesa
    @ManyToOne
    private Account account;

    //Classificacao do tipo de receita
    private RecipeType recipeType;

    //Valor no tipo moeda para salvar o saldo
    private BigDecimal amount;
    private BigDecimal amountRecipe;

    //Data de previsao de recebimento e data efetiva do recebimento
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate receivingDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate expectedReceivingDate;

    //Descricao da receita
    private String description;
}
