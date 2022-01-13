package br.com.desafiopubfuture.controllers;


import br.com.desafiopubfuture.dto.ObjectDto;
import br.com.desafiopubfuture.dto.ReceitaDto;
import br.com.desafiopubfuture.model.Receita;
import br.com.desafiopubfuture.service.ReceitaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping(value = "receitas")
public class ReceitasController {

    @Autowired
    private ReceitaService receitaService;

    @ApiOperation(value = "Retorna uma lista de receitas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de receita"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(value = "listar", produces = "application/json")
    public ResponseEntity<List<Receita>> listar() {
        return receitaService.getListaReceitas();
    }

    @ApiOperation(value = "Retorna uma receitas utilizando o ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a receita"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(value = "/localizarByIed/{id}", produces = "application/json")
    public ResponseEntity<Receita> findById(@PathVariable Long id) {
        return receitaService.getReceita(id);
    }

    @ApiOperation(value = "Retorna uma lista de receitas que contém a descrição")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de receita"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(value = "listarByDescricao", produces = "application/json")
    public ResponseEntity<List<Receita>> listarDescricao(String descricao) {
        return receitaService.getListaReceitasByDescricao(descricao);
    }

    @ApiOperation(value = "Retorna as despesas por data de recebimento de um periodo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o total das despesas"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(value = "listarByDataRecebimentoPeriodo/{inicio}/{fim}", produces = "application/json")
    public ResponseEntity<List<Receita>> listarRecitaDataRecebimentoPeriodo(LocalDate inicio, LocalDate fim) {
        return receitaService.getListaReceitasBetweenDataRecebimento(inicio, fim);
    }

    @ApiOperation(value = "Retorna o total de receitas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Valor total de receitas"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(value = "/totalReceitas", produces = "application/json")
    public ResponseEntity<BigDecimal> getTotalReceitas() {
        return receitaService.getTotalReceitas();
    }

    @ApiOperation(value = "Salva dados da receita")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os dados da receita salva no banco"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/registrarReceita", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Receita> salvar(@Validated @RequestBody ReceitaDto receita) {
        return receitaService.salvar(receita);
    }

    @ApiOperation(value = "Altera a data e o valor da receita")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o total das receitas"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PatchMapping(value = "/alterarContaValorDataPagamento/{id}/{valor}/{dataRecebimento}", produces = "application/json")
    public ResponseEntity<Receita> alterarDespesasDataPagamento(Long id, BigDecimal valor, LocalDate dataRecebimento) {
        return receitaService.atualizarValorDataRecebimento(id, valor, dataRecebimento);
    }

    @ApiOperation(value = "Atualiza dados da receita")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os dados da receita atualizado no banco"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping(value = "/atualizar/{id}", produces = "application/json")
    public ResponseEntity<Receita> atualizar(@Validated @RequestBody ReceitaDto receita, @PathVariable Long id) {
        return receitaService.atualizar(id, receita);
    }

    @ApiOperation(value = "Exclui uma receita utilizando o ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exclusão com sucesso pessoa"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping(value = "excluir/{id}", produces = "application/json")
    public ResponseEntity<ObjectDto> excluir(@PathVariable Long id) {
        return receitaService.excluir(id);
    }
}
