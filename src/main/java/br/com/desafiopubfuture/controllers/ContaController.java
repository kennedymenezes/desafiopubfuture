package br.com.desafiopubfuture.controllers;

import br.com.desafiopubfuture.dto.ContaDto;
import br.com.desafiopubfuture.dto.ObjectDto;
import br.com.desafiopubfuture.model.Conta;
import br.com.desafiopubfuture.service.ContaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @ApiOperation(value = "Retorna uma lista de conta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de conta"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(value = "listar", produces = "application/json")
    public ResponseEntity<List<Conta>> listar() {
        return contaService.getListaContas();
    }

    @ApiOperation(value = "Retorna uma conta utilizando o ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a conta"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(value = "localizarById/{id}", produces = "application/json")
    public ResponseEntity<Conta> findById(@PathVariable Long id) {
        return contaService.getConta(id);
    }

    @ApiOperation(value = "Salva dados da conta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os dados da conta salva no banco"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping(value = "registrar", produces = "application/json")
    public ResponseEntity<Conta> salvar(@Validated @RequestBody ContaDto conta) {
        return contaService.salvar(conta);
    }

    @ApiOperation(value = "Atualiza dados da conta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os dados da conta atualizado no banco"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping(value = "atualizar/{id}", produces = "application/json")
    public ResponseEntity<Conta> atualizar(@Validated @RequestBody ContaDto conta, @PathVariable Long id) {
        return contaService.atualizar(id, conta);
    }

    @ApiOperation(value = "Atualiza saldo da conta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os dados da conta atualizado no banco"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PatchMapping(value = "atualizarSaldo/{id}/{saldo}", produces = "application/json")
    public ResponseEntity<Conta> atualizar(@PathVariable Long id, @PathVariable BigDecimal saldo) {
        return contaService.atualizarSaldo(id, saldo);
    }

    @ApiOperation(value = "Exclui uma conta utilizando o ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exclusão com sucesso conta"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping(value = "excluir/{id}", produces = "application/json")
    public ResponseEntity<ObjectDto> excluir(@PathVariable Long id) {
        return contaService.excluir(id);
    }
}
