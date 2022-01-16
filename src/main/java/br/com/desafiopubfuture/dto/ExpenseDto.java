package br.com.desafiopubfuture.dto;

import br.com.desafiopubfuture.enums.ExpenseType;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ExpenseDto {

    @NotNull
    private Long accountId;

    @NotNull
    private ExpenseType expenseType;

    @NotNull
    private BigDecimal amount;

    private BigDecimal amountPaid;

    private LocalDate paymenteDate;
    private LocalDate expectedPaymentDate;

    private String description;
}
