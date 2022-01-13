package br.com.desafiopubfuture.service;

import br.com.desafiopubfuture.dto.DespesaDto;
import br.com.desafiopubfuture.dto.ObjectDto;
import br.com.desafiopubfuture.enums.TipoDespesa;
import br.com.desafiopubfuture.model.Despesa;
import br.com.desafiopubfuture.repository.DespesaRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class DespesaService extends BaseService {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private ContaService contaService;

    //Pesquisa em lista
    public ResponseEntity<List<Despesa>> getListaDespesas() throws ServiceException {
        return new ResponseEntity<>(despesaRepository.findAll(), HttpStatus.OK);
    }

    //Pesquisa em lista contendo a descricao
    public ResponseEntity<List<Despesa>> getListaDespesasDescricao(String descricao) throws ServiceException {
        return new ResponseEntity<List<Despesa>>(despesaRepository.findByDescricaoContains(descricao), HttpStatus.OK);
    }

    //Pesquisa em lista contendo a descricao
    public ResponseEntity<List<Despesa>> getListaDespesasTipos(TipoDespesa tipoDespesa) throws ServiceException {
        return new ResponseEntity<List<Despesa>>(despesaRepository.findByTipoDespesa(tipoDespesa), HttpStatus.OK);
    }

    //Pesquisa em lista pelo intervado de datas de pagamento
    public ResponseEntity<List<Despesa>> getListaDespesasDataPagamento(LocalDate inicio, LocalDate fim) throws ServiceException {
        return new ResponseEntity<>(despesaRepository.findByDataPagamentoBetween(inicio, fim), HttpStatus.OK);
    }

    //Obtem o total de despesas
    public ResponseEntity<BigDecimal> getTotalDespesa() throws ServiceException {
        return new ResponseEntity<>(despesaRepository.totalDespesas(), HttpStatus.OK);
    }

    //Pesquisa de 1 único resultado por um campo unico
    public ResponseEntity<Despesa> getDespesa(Long id) {
        return new ResponseEntity<Despesa>(despesaRepository.findById(id).get(), HttpStatus.OK);
    }

    //Salvando o registro criando
    public ResponseEntity<Despesa> salvar(DespesaDto objDto) throws ServiceException {
        Despesa obj = modelMapper.map(objDto, Despesa.class);
        obj.setDataCadastro(LocalDate.now());
        obj.setDataAtualizacao(LocalDate.now());
        obj.setConta(contaService.getContaObject(objDto.getContaId()));

        return new ResponseEntity<>(despesaRepository.save(obj), HttpStatus.OK);
    }

    //Salvando o registro atualizando
    public ResponseEntity<Despesa> atualizar(Long id, DespesaDto objDto) throws ServiceException {
        Despesa obj = despesaRepository.findById(id).get();
        obj.setDataAtualizacao(LocalDate.now());

        obj.setValor(objDto.getValor());
        obj.setDataPagamentoEsperado(objDto.getDataPagamentoEsperado());
        obj.setDataPagamento(objDto.getDataPagamento());
        obj.setTipoDespesa(objDto.getTipoDespesa());
        obj.setDescricao(objDto.getDescricao());
        obj.setConta(contaService.getContaObject(objDto.getContaId()));

        return new ResponseEntity<>(despesaRepository.save(obj), HttpStatus.OK);
    }

    //Atualizando somente a valor e data
    public ResponseEntity<Despesa> atualizarValorDataPagamento(Long id, BigDecimal valor, LocalDate dataPagamento) throws ServiceException {
        Despesa obj = despesaRepository.findById(id).get();
        obj.setDataAtualizacao(LocalDate.now());

        obj.setValor(valor);
        obj.setDataPagamento(dataPagamento);

        contaService.atualizarSaldo(obj.getConta().getId(), obj.getConta().getSaldo().min(valor));

        return new ResponseEntity<>(despesaRepository.save(obj), HttpStatus.OK);
    }

    //Excluindo o registro
    public ResponseEntity<ObjectDto> excluir(Long id) throws ServiceException {
        despesaRepository.deleteById(id);
        ObjectDto exclusaoDto = new ObjectDto();
        exclusaoDto.setId(id);
        exclusaoDto.setMensagem("Despesa: Registro exluído com sucesso");
        return new ResponseEntity<ObjectDto>(exclusaoDto, HttpStatus.OK);
    }

}
