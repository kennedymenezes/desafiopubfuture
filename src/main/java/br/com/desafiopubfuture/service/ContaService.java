package br.com.desafiopubfuture.service;

import br.com.desafiopubfuture.dto.ContaDto;
import br.com.desafiopubfuture.model.Conta;
import br.com.desafiopubfuture.model.Pessoa;
import br.com.desafiopubfuture.repository.ContaRepository;
import br.com.desafiopubfuture.repository.PessoaRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class ContaService extends BaseService {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    //Pesquisa em lista
    public ResponseEntity<Iterable<Conta>> getListaContas() throws ServiceException {
        return new ResponseEntity<>(contaRepository.findAll(), HttpStatus.OK);
    }

    //Pesquisa de 1 único resultado por um campo unico
    public ResponseEntity<Conta> getConta(Integer id) {
        return new ResponseEntity<>(contaRepository.findById(id), HttpStatus.OK);
    }

    //Salvando o registro (criando e atualizando
    public ResponseEntity<Conta> salvar(ContaDto objDto) throws ServiceException {
        Conta obj = modelMapper.map(objDto, Conta.class);
        obj.setDataCadastro(new Date());

        obj.setPessoa(getPessoa(objDto.getPessoaId()));

        return new ResponseEntity<>(contaRepository.save(obj), HttpStatus.OK);
    }

    //Salvando o registro atualizando
    public ResponseEntity<Conta> atualizar(Integer id, ContaDto objDto) throws ServiceException {
        Conta obj = contaRepository.findById(id);
        obj.setDataAtualizacao(new Date());
        obj.setContaCorrente(objDto.getContaCorrente());
        obj.setAgencia(objDto.getAgencia());
        obj.setNomeInstituicaoFinanceira(objDto.getNomeInstituicaoFinanceira());
        obj.setTipoConta(objDto.getTipoConta());
        obj.setSaldo(objDto.getSaldo());

        obj.setPessoa(getPessoa(objDto.getPessoaId()));

        return new ResponseEntity<>(contaRepository.save(obj), HttpStatus.OK);
    }

    //Atualizar somente o saldo
    public ResponseEntity<Conta> atualizarSaldo(Integer id, BigDecimal saldo) throws ServiceException {
        Conta obj = contaRepository.findById(id);
        obj.setDataAtualizacao(new Date());
        obj.setSaldo(saldo);

        return new ResponseEntity<>(contaRepository.save(obj), HttpStatus.OK);
    }

    //Transfere o saldo entre contas
    public ResponseEntity<Conta> transferenciaSaldo(Integer idOrigem, Integer idDestino) throws ServiceException {
        Conta obj1 = contaRepository.findById(idOrigem);
        Conta obj2 = contaRepository.findById(idDestino);

        obj2.setDataAtualizacao(new Date());
        obj2.setSaldo(obj2.getSaldo().add(obj1.getSaldo()));

        obj1.setDataAtualizacao(new Date());
        obj1.setSaldo(obj1.getSaldo().min(obj1.getSaldo()));

        return new ResponseEntity<>(contaRepository.save(obj2), HttpStatus.OK);
    }

    //Excluindo o registro
    public ResponseEntity<Void> excluir(Integer id) throws ServiceException {
        contaRepository.deleteById(id.longValue());
        return ResponseEntity.noContent().build();
    }

    private Pessoa getPessoa(Integer id) {
        return pessoaRepository.findById(id);
    }
}
