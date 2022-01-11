package br.com.desafiopubfuture.controllers;

import br.com.desafiopubfuture.dto.ContaDto;
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

@RestController
public class ContaController {
    @Autowired
    private ContaService contaService;

    @ApiOperation(value = "Retorna uma lista de contas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de conta"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/listarContas", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Iterable<Conta>> listar() {
        return contaService.getListaContas();
    }

    @ApiOperation(value = "Retorna uma contas utilizando o ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a conta"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/localizarContaId/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Conta> findById(@PathVariable Integer id) {
        return contaService.getConta(id);
    }

    @ApiOperation(value = "Salva dados da conta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os dados da conta salva no banco"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/registrarConta", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Conta> salvar(@Validated @RequestBody ContaDto conta) {
        return contaService.salvar(conta);
    }

    @ApiOperation(value = "Atualiza dados da conta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os dados da conta atualizado no banco"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/atualizarConta/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Conta> atualizar(@Validated @RequestBody ContaDto contaDto, @PathVariable Integer id) {
        return contaService.atualizar(id, contaDto);
    }

    @ApiOperation(value = "Exclui uma conta utilizando o ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exclusão com sucesso "),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/excluirConta/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        return contaService.excluir(id);
    }

    @ApiOperation(value = "Atualizar saldo da conta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Saldo atualizado com sucesso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/atualizarSaldoConta/{id}/{saldo}", method = RequestMethod.PATCH, produces = "application/json")
    public ResponseEntity<Conta> atualizarSaldo(@PathVariable Integer id, @PathVariable BigDecimal saldo) {
        return contaService.atualizarSaldo(id, saldo);
    }

    @ApiOperation(value = "Transfere saldo da contas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Saldo atualizado com sucesso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/transferirSaldoConta/{idOrigem}/{idDestino}", method = RequestMethod.PATCH, produces = "application/json")
    public ResponseEntity<Conta> transferirSaldoConta(@PathVariable Integer id, @PathVariable Integer destino) {
        return contaService.transferenciaSaldo(id, destino);
    }
}
