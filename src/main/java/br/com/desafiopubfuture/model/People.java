package br.com.desafiopubfuture.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity

@Getter
@Setter
public class People implements Serializable {
    private static final long serialVersionUID = 1L;

    //ID da pessoa
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Data de cadastro e atualizacao do registro
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate registrationDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate updateDate;

    //1 documento 1 pessoa
    @Column(unique = true)
    private String document;

    //Nome da pessoa e email
    private String name;
    private String email;

}
