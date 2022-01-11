package br.com.desafiopubfuture.service;

import br.com.desafiopubfuture.dto.ReceitaDto;
import br.com.desafiopubfuture.enums.TipoReceita;
import br.com.desafiopubfuture.model.Conta;
import br.com.desafiopubfuture.model.Receita;
import br.com.desafiopubfuture.repository.ContaRepository;
import br.com.desafiopubfuture.repository.ReceitaRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class ReceitaService extends BaseService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private ContaRepository contaRepository;

    //Pesquisa em lista
    public ResponseEntity<Iterable<Receita>> getListaReceitas() throws ServiceException {
        return new ResponseEntity<>(receitaRepository.findAll(), HttpStatus.OK);
    }

    //Pesquisa em lista
    public ResponseEntity<Iterable<Receita>> getListaReceitasTipos(TipoReceita tipoReceita) throws ServiceException {
        return new ResponseEntity<>(receitaRepository.findByTipoReceita(tipoReceita), HttpStatus.OK);
    }

    //Pesquisa em lista
    public ResponseEntity<Iterable<Receita>> getListaReceitasByDescricao(String descricao) throws ServiceException {
        return new ResponseEntity<>(receitaRepository.findByDescricaoContains(descricao), HttpStatus.OK);
    }

    //Pesquisa em lista
    public ResponseEntity<Iterable<Receita>> getListaReceitasBetweenDataRecebimento(Date inicio, Date fim) throws ServiceException {
        return new ResponseEntity<>(receitaRepository.findByDataRecebimentoBetween(inicio, fim), HttpStatus.OK);
    }

    //Pesquisa de 1 único resultado por um campo unico
    public ResponseEntity<Receita> getReceita(Integer id) {
        return new ResponseEntity<>(receitaRepository.findById(id), HttpStatus.OK);
    }

    //Obtem o total de despesas
    public ResponseEntity<BigDecimal> getTotalReceitas() throws ServiceException {
        return new ResponseEntity<>(receitaRepository.totalReceitas(), HttpStatus.OK);
    }

    //Salvando o registro criando
    public ResponseEntity<Receita> salvar(ReceitaDto objDto) throws ServiceException {
        Receita obj = modelMapper.map(objDto, Receita.class);
        obj.setDataCadastro(new Date());
        return new ResponseEntity<>(receitaRepository.save(obj), HttpStatus.OK);
    }

    //Salvando o registro atualizando
    public ResponseEntity<Receita> atualizar(Integer id, ReceitaDto objDto) throws ServiceException {
        Receita obj = receitaRepository.findById(id);
        obj.setDataAtualizacao(new Date());
        obj.setValor(objDto.getValor());
        obj.setTipoReceita(objDto.getTipoReceita());
        obj.setDataRecebimento(objDto.getDataRecebimento());
        obj.setDataRecebimentoEsperado(objDto.getDataRecebimentoEsperado());
        obj.setDescricao(objDto.getDescricao());
        obj.setConta(getConta(objDto.getContaId()));

        return new ResponseEntity<>(receitaRepository.save(obj), HttpStatus.OK);
    }

    //Salvando o registro atualizando
    public ResponseEntity<Receita> atualizarValorDataRecebimento(Integer id, BigDecimal valor, Date dataRecebimento) throws ServiceException {
        Receita obj = receitaRepository.findById(id);
        obj.setDataAtualizacao(new Date());
        obj.setValor(valor);
        obj.setDataRecebimento(dataRecebimento);

        return new ResponseEntity<>(receitaRepository.save(obj), HttpStatus.OK);
    }

    //Excluindo o registro
    public ResponseEntity<Void> excluir(Integer id) throws ServiceException {
        receitaRepository.deleteById(id.longValue());
        return ResponseEntity.noContent().build();
    }

    private Conta getConta(Integer id) {
        return contaRepository.findById(id);
    }
}
