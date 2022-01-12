package br.com.desafiopubfuture.controllers;

import br.com.desafiopubfuture.dto.DespesaDto;
import br.com.desafiopubfuture.dto.ObjectDto;
import br.com.desafiopubfuture.enums.TipoDespesa;
import br.com.desafiopubfuture.model.Despesa;
import br.com.desafiopubfuture.service.DespesaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "despesas")
public class DespesasController {

    @Autowired
    private DespesaService despesaService;

    @ApiOperation(value = "Retorna uma lista de despesas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de despesa"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(value = "/listarDespesas", produces = "application/json")
    public ResponseEntity<Iterable<Despesa>> listar() {
        return despesaService.getListaDespesas();
    }

    @ApiOperation(value = "Retorna uma lista de despesas de um determinado tipo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de despesa"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(value = "/listarByTipoDespesa", produces = "application/json")
    public ResponseEntity<Iterable<Despesa>> listarPorTipoDespesa(TipoDespesa tipoDespesa) {
        return despesaService.getListaDespesasTipos(tipoDespesa);
    }

    @ApiOperation(value = "Retorna uma lista de despesas que contém a descricao")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de despesa"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(value = "/listarByDescricao", produces = "application/json")
    public ResponseEntity<Iterable<Despesa>> listarDescricao(String descricao) {
        return despesaService.getListaDespesasDescricao(descricao);
    }

    @ApiOperation(value = "Retorna o total de despesas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o total das despesas"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(value = "/totalDespesas", produces = "application/json")
    public ResponseEntity<BigDecimal> totalDespesas() {
        return despesaService.getTotalDespesa();
    }

    @ApiOperation(value = "Retorna as despesas por data de pagamento de um periodo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o total das despesas"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(value = "/listarByDataPagamento/{inicio}/{fim}", produces = "application/json")
    public ResponseEntity<Iterable<Despesa>> listarDespesasDataPagamentoPeriodo(LocalDate inicio, LocalDate fim) {
        return despesaService.getListaDespesasDataPagamento(inicio, fim);
    }

    @ApiOperation(value = "Altera a data e o valor da despesa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o total das despesas"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PatchMapping(value = "/alterarByValorDataPagamento/{id}/{valor}/{dataPagamento}", produces = "application/json")
    public ResponseEntity<Despesa> alterarDespesasDataPagamento(Long id, BigDecimal valor, LocalDate dataPagamento) {
        return despesaService.atualizarValorDataPagamento(id, valor, dataPagamento);
    }


    @ApiOperation(value = "Retorna uma despesas utilizando o ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a despesa"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(value = "/localizarById/{id}", produces = "application/json")
    public ResponseEntity<Despesa> findById(@PathVariable Long id) {
        return despesaService.getDespesa(id);
    }

    @ApiOperation(value = "Salva dados da despesa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os dados da despesa salva no banco"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping(value = "/registrar", produces = "application/json")
    public ResponseEntity<Despesa> salvar(@Validated @RequestBody DespesaDto despesa) {
        return despesaService.salvar(despesa);
    }

    @ApiOperation(value = "Atualiza dados da despesa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os dados da despesa atualizado no banco"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping(value = "/atualizar/{id}", produces = "application/json")
    public ResponseEntity<Despesa> atualizar(@Validated @RequestBody DespesaDto despesa, @PathVariable Long id) {
        return despesaService.atualizar(id, despesa);
    }

    @ApiOperation(value = "Exclui uma despesas utilizando o ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exclusão com sucesso despesa"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping(value = "/excluir/{id}", produces = "application/json")
    public ResponseEntity<ObjectDto> excluir(@PathVariable Long id) {
        return despesaService.excluir(id);
    }
}
