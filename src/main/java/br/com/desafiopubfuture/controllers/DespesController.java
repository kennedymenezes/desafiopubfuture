package br.com.desafiopubfuture.controllers;

import br.com.desafiopubfuture.dto.DespesaDto;
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
import java.util.Date;

@RestController
public class DespesController {
    @Autowired
    private DespesaService despesaService;

    @ApiOperation(value = "Retorna uma lista de despesas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de despesa"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/listarDespesas", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Iterable<Despesa>> listar() {
        return despesaService.getListaDespesas();
    }

    @ApiOperation(value = "Retorna uma lista de despesas de um determinado tipo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de despesa"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/listarDespesasPorTipoDespesa", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Iterable<Despesa>> listarPorTipoDespesa(TipoDespesa tipoDespesa) {
        return despesaService.getListaDespesasTipos(tipoDespesa);
    }

    @ApiOperation(value = "Retorna uma lista de despesas que contém a descricao")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de despesa"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/listarDespesasDescricao", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Iterable<Despesa>> listarDescricao(String descricao) {
        return despesaService.getListaDespesasDescricao(descricao);
    }

    @ApiOperation(value = "Retorna o total de despesas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o total das despesas"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/totalDespesas", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<BigDecimal> totalDespesas() {
        return despesaService.getTotalDespesa();
    }

    @ApiOperation(value = "Retorna as despesas por data de pagamento de um periodo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o total das despesas"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/listarDespesasDataPagamento/{inicio}/{fim}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Iterable<Despesa>> listarDespesasDataPagamentoPeriodo(Date inicio, Date fim) {
        return despesaService.getListaDespesasDataPagamento(inicio, fim);
    }

    @ApiOperation(value = "Altera a data e o valor da despesa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o total das despesas"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/alterarDespesaValorDataPagamento/{id}/{valor}/{dataPagamento}", method = RequestMethod.PATCH, produces = "application/json")
    public ResponseEntity<Despesa> alterarDespesasDataPagamento(Integer id, BigDecimal valor, Date dataPagamento) {
        return despesaService.atualizarValorDataPagamento(id, valor, dataPagamento);
    }


    @ApiOperation(value = "Retorna uma despesas utilizando o ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a despesa"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/localizarDespesaId/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Despesa> findById(@PathVariable Integer id) {
        return despesaService.getDespesa(id);
    }

    @ApiOperation(value = "Salva dados da despesa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os dados da despesa salva no banco"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/registrarDespesa", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Despesa> salvar(@Validated @RequestBody DespesaDto despesa) {
        return despesaService.salvar(despesa);
    }

    @ApiOperation(value = "Atualiza dados da despesa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os dados da despesa atualizado no banco"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/atualizarDespesa/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Despesa> atualizar(@Validated @RequestBody DespesaDto despesa, @PathVariable Integer id) {
        return despesaService.atualizar(id, despesa);
    }

    @ApiOperation(value = "Exclui uma despesas utilizando o ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exclusão com sucesso despesa"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/excluirDespesa/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        return despesaService.excluir(id);
    }
}
