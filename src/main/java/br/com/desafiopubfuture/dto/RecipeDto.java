package br.com.desafiopubfuture.dto;

import br.com.desafiopubfuture.enums.RecipeType;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class RecipeDto {

    @NotNull
    private Long accountId;

    @NotNull
    private RecipeType typeRecipe;

    @NotNull
    private BigDecimal amount;

    private BigDecimal amountReceived;

    private LocalDate receivingDate;
    private LocalDate expectedReceivingDate;

    private String description;
}
