package br.com.desafiopubfuture.service;

import br.com.desafiopubfuture.dto.DespesaDto;
import br.com.desafiopubfuture.enums.TipoDespesa;
import br.com.desafiopubfuture.model.Conta;
import br.com.desafiopubfuture.model.Despesa;
import br.com.desafiopubfuture.repository.ContaRepository;
import br.com.desafiopubfuture.repository.DespesaRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class DespesaService extends BaseService {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private ContaRepository contaRepository;

    //Pesquisa em lista
    public ResponseEntity<Iterable<Despesa>> getListaDespesas() throws ServiceException {
        return new ResponseEntity<>(despesaRepository.findAll(), HttpStatus.OK);
    }

    //Pesquisa em lista contendo a descricao
    public ResponseEntity<Iterable<Despesa>> getListaDespesasDescricao(String descricao) throws ServiceException {
        return new ResponseEntity<>(despesaRepository.findByDescricaoContains(descricao), HttpStatus.OK);
    }

    //Pesquisa em lista contendo a descricao
    public ResponseEntity<Iterable<Despesa>> getListaDespesasTipos(TipoDespesa tipoDespesa) throws ServiceException {
        return new ResponseEntity<>(despesaRepository.findByTipoDespesa(tipoDespesa), HttpStatus.OK);
    }

    //Pesquisa em lista pelo intervado de datas de pagamento
    public ResponseEntity<Iterable<Despesa>> getListaDespesasDataPagamento(Date inicio, Date fim) throws ServiceException {
        return new ResponseEntity<>(despesaRepository.findByDataPagamentoBetween(inicio, fim), HttpStatus.OK);
    }

    //Obtem o total de despesas
    public ResponseEntity<BigDecimal> getTotalDespesa() throws ServiceException {
        return new ResponseEntity<>(despesaRepository.totalDespesas(), HttpStatus.OK);
    }

    //Pesquisa de 1 único resultado por um campo unico
    public ResponseEntity<Despesa> getDespesa(Integer id) {
        return new ResponseEntity<>(despesaRepository.findById(id), HttpStatus.OK);
    }

    //Salvando o registro criando
    public ResponseEntity<Despesa> salvar(DespesaDto objDto) throws ServiceException {
        Despesa obj = modelMapper.map(objDto, Despesa.class);
        obj.setDataCadastro(new Date());

        obj.setConta(getConta(objDto.getContaId()));

        return new ResponseEntity<>(despesaRepository.save(obj), HttpStatus.OK);
    }

    //Salvando o registro atualizando
    public ResponseEntity<Despesa> atualizar(Integer id, DespesaDto objDto) throws ServiceException {
        Despesa obj = despesaRepository.findById(id);
        obj.setDataAtualizacao(new Date());

        obj.setValor(objDto.getValor());
        obj.setDataPagamentoEsperado(objDto.getDataPagamentoEsperado());
        obj.setDataPagamento(objDto.getDataPagamento());
        obj.setTipoDespesa(objDto.getTipoDespesa());
        obj.setDescricao(objDto.getDescricao());
        obj.setConta(getConta(objDto.getContaId()));

        return new ResponseEntity<>(despesaRepository.save(obj), HttpStatus.OK);
    }

    //Atualizando somente a valor e data
    public ResponseEntity<Despesa> atualizarValorDataPagamento(Integer id, BigDecimal valor, Date dataPagamento) throws ServiceException {
        Despesa obj = despesaRepository.findById(id);
        obj.setDataAtualizacao(new Date());

        obj.setValor(valor);
        obj.setDataPagamento(dataPagamento);

        return new ResponseEntity<>(despesaRepository.save(obj), HttpStatus.OK);
    }

    //Excluindo o registro
    public ResponseEntity<Void> excluir(Integer id) throws ServiceException {
        despesaRepository.deleteById(id.longValue());
        return ResponseEntity.noContent().build();
    }

    private Conta getConta(Integer id) {
        return contaRepository.findById(id);
    }
}
