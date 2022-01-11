package br.com.desafiopubfuture.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity

@Getter
@Setter
@ConstructorBinding
public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    //ID da pessoa
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //Data de cadastro e atualizacao do registro
    private Date dataCadastro;
    private Date dataAtualizacao;

    //1 documento 1 pessoa
    @Column(unique = true)
    private String documento;

    //Nome da pessoa e email
    private String nome;
    private String email;

}
