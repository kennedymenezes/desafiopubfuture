package br.com.desafiopubfuture.dto;

import br.com.desafiopubfuture.enums.AccountType;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountDto {
    @NotNull
    private Long peopleId;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private AccountType accountType;

    private String financialInttitutionNumber;
    private String financialInstitutionName;
    private String agency;
    private String chekingAccount;
}
