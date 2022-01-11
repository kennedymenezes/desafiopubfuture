package br.com.desafiopubfuture.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDto {

    @NotNull
    private String documento;
    @NotNull
    private String nome;

    private String email;

}
