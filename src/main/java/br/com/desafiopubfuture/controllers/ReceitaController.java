package br.com.desafiopubfuture.controllers;

import br.com.desafiopubfuture.dto.ReceitaDto;
import br.com.desafiopubfuture.enums.TipoReceita;
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
import java.util.Date;

@RestController
public class ReceitaController {
    @Autowired
    private ReceitaService receitaService;

    @ApiOperation(value = "Retorna uma lista de receitas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de receita"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/listarReceitas", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Iterable<Receita>> listar() {
        return receitaService.getListaReceitas();
    }

    @ApiOperation(value = "Retorna uma lista de receitas de um determinado tipo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de receita"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/listarReceitoTipos", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Iterable<Receita>> listarReceitoTipos(TipoReceita tipoReceita) {
        return receitaService.getListaReceitasTipos(tipoReceita);
    }

    @ApiOperation(value = "Retorna uma lista de receitas que contém a descrição")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de receita"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/listarReceitasDescricao", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Iterable<Receita>> listarDescricao(String descricao) {
        return receitaService.getListaReceitasByDescricao(descricao);
    }


    @ApiOperation(value = "Retorna as despesas por data de recebimento de um periodo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o total das despesas"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/listarRecitaDataRecebimentoPeriodo/{inicio}/{fim}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Iterable<Receita>> listarRecitaDataRecebimentoPeriodo(Date inicio, Date fim) {
        return receitaService.getListaReceitasBetweenDataRecebimento(inicio, fim);
    }

    @ApiOperation(value = "Retorna o total de receitas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Valor total de receitas"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/totalReceitas", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<BigDecimal> getTotalReceitas() {
        return receitaService.getTotalReceitas();
    }

    @ApiOperation(value = "Retorna uma receitas utilizando o ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a receita"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/localizarReceitaId/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Receita> findById(@PathVariable Integer id) {
        return receitaService.getReceita(id);
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
    @RequestMapping(value = "/alterarContaValorDataPagamento/{id}/{valor}/{dataRecebimento}", method = RequestMethod.PATCH, produces = "application/json")
    public ResponseEntity<Receita> alterarDespesasDataPagamento(Integer id, BigDecimal valor, Date dataRecebimento) {
        return receitaService.atualizarValorDataRecebimento(id, valor, dataRecebimento);
    }

    @ApiOperation(value = "Atualiza dados da receita")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os dados da receita atualizado no banco"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/atualizarReceita/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Receita> atualizar(@Validated @RequestBody ReceitaDto receita, @PathVariable Integer id) {
        return receitaService.atualizar(id, receita);
    }

    @ApiOperation(value = "Exclui uma receitas utilizando o ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exclusão com sucesso receita"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/excluirReceita/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        return receitaService.excluir(id);
    }
}
