package br.com.desafiopubfuture.model;

import br.com.desafiopubfuture.enums.AccountType;
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
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    //ID da conta
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Define N(Contas) -> 1 (Pessoa)
    @ManyToOne
    private People people;

    //Data de cadastro e atualizacao do registro
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate registrationDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate updateDate;

    //Saldo atual da conta
    private BigDecimal amount;

    //Tipo de conta
    private AccountType accountType;

    //Número da instituicao financeira, nome, agência e conta corrente
    private String financialInttitutionNumber;
    private String financialInstitutionName;
    private String agency;
    private String chekingAccount;

}
