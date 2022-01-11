package br.com.desafiopubfuture;

import br.com.desafiopubfuture.dto.ContaDto;
import br.com.desafiopubfuture.dto.DespesaDto;
import br.com.desafiopubfuture.dto.PessoaDto;
import br.com.desafiopubfuture.dto.ReceitaDto;
import br.com.desafiopubfuture.enums.TipoConta;
import br.com.desafiopubfuture.enums.TipoDespesa;
import br.com.desafiopubfuture.enums.TipoReceita;
import br.com.desafiopubfuture.model.Conta;
import br.com.desafiopubfuture.model.Despesa;
import br.com.desafiopubfuture.model.Pessoa;
import br.com.desafiopubfuture.model.Receita;
import br.com.desafiopubfuture.service.ContaService;
import br.com.desafiopubfuture.service.DespesaService;
import br.com.desafiopubfuture.service.PessoaService;
import br.com.desafiopubfuture.service.ReceitaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DesafiopubfutureApplicationTests {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ContaService contaService;

    @Autowired
    private DespesaService despesaService;

    @Autowired
    private ReceitaService receitaService;
    /**
     * Testes de CRUDs
     */

    //Pessoas
    @Test
    void insertPessoa(){
        PessoaDto pessoa = new PessoaDto();
        pessoa.setDocumento("65436543654323");
        pessoa.setEmail("pessoa01@email.com");
        pessoa.setNome("Pessoa 01 nome");
        pessoaService.salvar(pessoa);
        Integer c = pessoaService.getListaPessoas().getHeaders().size();
        assertEquals (1, c);
    }


    //Contas
    @Test
    void insertConta(){
        ContaDto conta = new ContaDto();
        conta.setSaldo(new BigDecimal(598.78));
        conta.setContaCorrente("1234");
        conta.setTipoConta(TipoConta.ContaCorrente);
        conta.setAgencia("1234");
        conta.setContaCorrente("0989869");
        conta.setPessoaId(1);
        conta.setNomeInstituicaoFinanceira("Instituicao ASDF");
        conta.setNumeroInstituicaoFinanceira("345");
        contaService.salvar(conta);
        Integer c = contaService.getListaContas().getHeaders().size();
        assertEquals (1, c);
    }

    //Despesas
    @Test
    void insertDespesa() throws ParseException {
        DespesaDto despesa = new DespesaDto();
        despesa.setTipoDespesa(TipoDespesa.Alimentacao);
        despesa.setDescricao("Almoço XMTT");
        despesa.setValor(new BigDecimal(30.87));
        despesa.setContaId(1);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date data = format.parse("09/01/2022");
        despesa.setDataPagamento(data);
        despesa.setDataPagamentoEsperado(data);
        despesaService.salvar(despesa);
        Integer c = despesaService.getListaDespesas().getHeaders().size();
        assertEquals (1, c);
    }

    //Receitas
    @Test
    void insertReceita() throws ParseException {
        ReceitaDto receita = new ReceitaDto();
        receita.setTipoReceita(TipoReceita.Salario);
        receita.setDescricao("Salário XMTT");
        receita.setValor(new BigDecimal(30.87));
        receita.setContaId(2);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date data = format.parse("07/01/2022");
        receita.setDataRecebimento(data);
        receita.setDataRecebimentoEsperado(data);
        receitaService.salvar(receita);
        Integer c = receitaService.getListaReceitas().getHeaders().size();
        assertEquals (1, c);
    }
}
