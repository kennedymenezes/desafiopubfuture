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
public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    //ID da pessoa
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Data de cadastro e atualizacao do registro
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAtualizacao;

    //1 documento 1 pessoa
    @Column(unique = true)
    private String documento;

    //Nome da pessoa e email
    private String nome;
    private String email;

}
