package br.com.desafiopubfuture.model;

import br.com.desafiopubfuture.enums.ExpenseType;
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
public class Expense implements Serializable {
    private static final long serialVersionUID = 1L;

    //ID da despesa
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

    //Classificacao do tipo de despesa
    private ExpenseType expenseType;

    //Valor no tipo moeda para salvar o saldo
    private BigDecimal amount;
    private BigDecimal amountPaied;

    //Data de previsao de pagamento e data da efetivacao do pagamento
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate expectedPaymentDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate paymenteDate;

    //Descricao da despesa
    private String description;

}
