package br.com.desafiopubfuture.controllers;

import br.com.desafiopubfuture.dto.PessoaDto;
import br.com.desafiopubfuture.model.Pessoa;
import br.com.desafiopubfuture.repository.PessoaRepository;
import br.com.desafiopubfuture.service.PessoaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @ApiOperation(value = "Retorna uma lista de pessoas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de pessoa"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/listarPessoas", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Iterable<Pessoa>> listar() {
        return pessoaService.getListaPessoas();
    }

    @ApiOperation(value = "Retorna uma pessoas utilizando o ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a pessoa"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/localizarPessoaId/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Pessoa> findById(@PathVariable Integer id) {
        return pessoaService.getPessoa(id, "");
    }

    @ApiOperation(value = "Retorna uma pessoas utilizando o documento cadastrado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a pessoa"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/localizarPessoaDocumento/{documento}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Pessoa> findByDocumento(@PathVariable String documento) {
        return pessoaService.getPessoa(0, documento);
    }

    @ApiOperation(value = "Salva dados da pessoa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os dados da pessoa salva no banco"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/registrarPessoa", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Pessoa> salvar(@Validated @RequestBody PessoaDto pessoa) {
        return pessoaService.salvar(pessoa);
    }

    @ApiOperation(value = "Atualiza dados da pessoa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os dados da pessoa atualizado no banco"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/atualizarPessoa/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Pessoa> atualizar(@Validated @RequestBody PessoaDto pessoa, @PathVariable Integer id) {
        return pessoaService.atualizar(id, pessoa);
    }

    @ApiOperation(value = "Exclui uma pessoas utilizando o ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exclusão com sucesso pessoa"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/excluirPessoa/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        return pessoaService.excluir(id);
    }
}
