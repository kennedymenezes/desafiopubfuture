package br.com.desafiopubfuture.controllers;

import br.com.desafiopubfuture.dto.ObjectDto;
import br.com.desafiopubfuture.dto.PessoaDto;
import br.com.desafiopubfuture.model.Pessoa;
import br.com.desafiopubfuture.service.PessoaService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @ApiOperation(value = "Retorna uma lista de pessoas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de pessoa", examples = @Example(value = @ExampleProperty(value = "[]" ))),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(value = "listar", produces = "application/json")
    public ResponseEntity<List<Pessoa>> listar() {
        return pessoaService.getListaPessoas();
    }

    @ApiOperation(value = "Retorna uma pessoas utilizando o ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a pessoa"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(value = "localizarById/{id}", produces = "application/json")
    public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
        return pessoaService.getPessoaId(id);
    }

    @ApiOperation(value = "Retorna uma pessoas utilizando o documento cadastrado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a pessoa"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(value = "localizarByDocumento/{documento}", produces = "application/json")
    public ResponseEntity<Pessoa> findByDocumento(@PathVariable String documento) {
        return pessoaService.getPessoaDocumento(documento);
    }

    @ApiOperation(value = "Salva dados da pessoa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os dados da pessoa salva no banco"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping(value = "registrar", produces = "application/json")
    public ResponseEntity<Pessoa> salvar(@Validated @RequestBody PessoaDto pessoa) {
        return pessoaService.salvar(pessoa);
    }

    @ApiOperation(value = "Atualiza dados da pessoa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os dados da pessoa atualizado no banco"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping(value = "atualizar/{id}", produces = "application/json")
    public ResponseEntity<Pessoa> atualizar(@Validated @RequestBody PessoaDto pessoa, @PathVariable Long id) {
        return pessoaService.atualizar(id, pessoa);
    }

    @ApiOperation(value = "Atualiza somente nome e email da pessoa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os dados da pessoa atualizado no banco"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PatchMapping(value = "atualizarNomeEmail/{id}/{nome}/{email}", produces = "application/json")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @PathVariable String nome, @PathVariable String email) {
        return pessoaService.atualizarNomeEmail(id, nome, email);
    }

    @ApiOperation(value = "Exclui uma pessoas utilizando o ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exclusão com sucesso pessoa"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping(value = "excluir/{id}", produces = "application/json")
    public ResponseEntity<ObjectDto> excluir(@PathVariable Long id) {
        return pessoaService.excluir(id);
    }
}
