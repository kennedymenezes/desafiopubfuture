package br.com.desafiopubfuture.service;

import br.com.desafiopubfuture.dto.ObjectDto;
import br.com.desafiopubfuture.dto.ReceitaDto;
import br.com.desafiopubfuture.enums.TipoReceita;
import br.com.desafiopubfuture.model.Conta;
import br.com.desafiopubfuture.model.Receita;
import br.com.desafiopubfuture.repository.ReceitaRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Service
public class ReceitaService extends BaseService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private ContaService contaService;

    //Pesquisa em lista
    public ResponseEntity<Iterable<Receita>> getListaReceitas() throws ServiceException {
        return new ResponseEntity<>(receitaRepository.findAll(), HttpStatus.OK);
    }

    //Pesquisa em lista
    public ResponseEntity<Iterable<Receita>> getListaReceitasTipos(TipoReceita tipoReceita) throws ServiceException {
        return new ResponseEntity<Iterable<Receita>>(receitaRepository.findByTipoReceita(tipoReceita), HttpStatus.OK);
    }

    //Pesquisa em lista
    public ResponseEntity<Iterable<Receita>> getListaReceitasByDescricao(String descricao) throws ServiceException {
        return new ResponseEntity<Iterable<Receita>>(receitaRepository.findByDescricaoContains(descricao), HttpStatus.OK);
    }

    //Pesquisa em lista
    public ResponseEntity<Iterable<Receita>> getListaReceitasBetweenDataRecebimento(LocalDate inicio, LocalDate fim) throws ServiceException {
        return new ResponseEntity<Iterable<Receita>>(receitaRepository.findByDataRecebimentoBetween(inicio, fim), HttpStatus.OK);
    }

    //Pesquisa de 1 único resultado por um campo unico
    public ResponseEntity<Receita> getReceita(Long id) {
        return new ResponseEntity<Receita>(receitaRepository.findById(id).get(), HttpStatus.OK);
    }

    //Obtem o total de despesas
    public ResponseEntity<BigDecimal> getTotalReceitas() throws ServiceException {
        return new ResponseEntity<>(receitaRepository.totalReceitas(), HttpStatus.OK);
    }

    //Salvando o registro criando
    public ResponseEntity<Receita> salvar(ReceitaDto objDto) throws ServiceException {
        Receita obj = modelMapper.map(objDto, Receita.class);
        obj.setDataCadastro(LocalDate.now());
        obj.setDataAtualizacao(LocalDate.now());
        return new ResponseEntity<>(receitaRepository.save(obj), HttpStatus.OK);
    }

    //Salvando o registro atualizando
    public ResponseEntity<Receita> atualizar(Long id, ReceitaDto objDto) throws ServiceException {
        Receita obj = receitaRepository.findById(id).get();
        obj.setDataAtualizacao(LocalDate.now());
        obj.setValor(objDto.getValor());

        obj.setTipoReceita(objDto.getTipoReceita());
        obj.setDataRecebimentoEsperado(objDto.getDataRecebimentoEsperado());
        obj.setDescricao(objDto.getDescricao());
        if (objDto.getDataRecebimento() != null) {
            obj.setDataRecebimento(objDto.getDataRecebimento());
            obj.setValorRecebido(objDto.getValorRecebido());
            obj.setConta(contaService.getContaObject(objDto.getContaId()));
        }

        return new ResponseEntity<>(receitaRepository.save(obj), HttpStatus.OK);
    }

    //Salvando o registro atualizando
    public ResponseEntity<Receita> atualizarValorDataRecebimento(Long id, BigDecimal valor, LocalDate dataRecebimento) throws ServiceException {
        Receita obj = receitaRepository.findById(id).get();

        //Subtraindo o valor antigo do recebimento
        contaService.atualizarSaldo(obj.getConta().getId(),(obj.getValor().multiply(new BigDecimal( - 1))));

        obj.setDataAtualizacao(LocalDate.now());
        obj.setValor(valor);
        obj.setDataRecebimento(dataRecebimento);

        //Adicionando o valor do recebimento atualizado
        contaService.atualizarSaldo(obj.getConta().getId(),(obj.getValor().add(valor)));

        return new ResponseEntity<>(receitaRepository.save(obj), HttpStatus.OK);
    }

    //Excluindo o registro
    public ResponseEntity<ObjectDto> excluir(Long id) throws ServiceException {
        receitaRepository.deleteById(id);
        ObjectDto exclusaoDto = new ObjectDto();
        exclusaoDto.setId(id);
        exclusaoDto.setMensagem("Receita: Registro exluído com sucesso");
        return new ResponseEntity<ObjectDto>(exclusaoDto, HttpStatus.OK);
    }

}
