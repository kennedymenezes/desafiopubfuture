package br.com.desafiopubfuture.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeopleDto {

    @NotNull
    private String document;
    @NotNull
    private String name;

    private String email;

}
