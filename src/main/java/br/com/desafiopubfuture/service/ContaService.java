package br.com.desafiopubfuture.service;

import br.com.desafiopubfuture.dto.ContaDto;
import br.com.desafiopubfuture.dto.ObjectDto;
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
import java.time.LocalDate;
import java.util.List;

@Service
public class ContaService extends BaseService {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    //Pesquisa em lista
    public ResponseEntity<List<Conta>> getListaContas() throws ServiceException {
        return new ResponseEntity<>(contaRepository.findAll(), HttpStatus.OK);
    }

    //Pesquisa de 1 único resultado por um campo unico
    public ResponseEntity<Conta> getConta(Long id) {
        return new ResponseEntity<Conta>(contaRepository.findById(id).get(), HttpStatus.OK);
    }

    public Conta getContaObject(Long id) {
        return contaRepository.findById(id).get();
    }

    //Salvando o registro (criando e atualizando
    public ResponseEntity<Conta> salvar(ContaDto objDto) throws ServiceException {
        Conta obj = modelMapper.map(objDto, Conta.class);
        obj.setDataCadastro(LocalDate.now());
        obj.setDataAtualizacao(LocalDate.now());
        obj.setPessoa(getPessoa(objDto.getPessoaId()));

        return new ResponseEntity<>(contaRepository.save(obj), HttpStatus.OK);
    }

    //Salvando o registro atualizando
    public ResponseEntity<Conta> atualizar(Long id, ContaDto objDto) throws ServiceException {
        Conta obj = contaRepository.findById(id).get();
        obj.setDataAtualizacao(LocalDate.now());
        obj.setContaCorrente(objDto.getContaCorrente());
        obj.setAgencia(objDto.getAgencia());
        obj.setNomeInstituicaoFinanceira(objDto.getNomeInstituicaoFinanceira());
        obj.setTipoConta(objDto.getTipoConta());
        obj.setSaldo(objDto.getSaldo());

        obj.setPessoa(getPessoa(objDto.getPessoaId()));

        return new ResponseEntity<>(contaRepository.save(obj), HttpStatus.OK);
    }

    //Atualizar somente o saldo
    public ResponseEntity<Conta> atualizarSaldo(Long id, BigDecimal saldo) throws ServiceException {
        Conta obj = contaRepository.findById(id).get();
        obj.setDataAtualizacao(LocalDate.now());
        obj.setSaldo(saldo);

        return new ResponseEntity<>(contaRepository.save(obj), HttpStatus.OK);
    }

    //Transfere o saldo entre contas
    public ResponseEntity<Conta> transferenciaSaldo(Long idOrigem, Long idDestino) throws ServiceException {
        Conta obj1 = contaRepository.findById(idOrigem).get();
        Conta obj2 = contaRepository.findById(idDestino).get();

        obj2.setDataAtualizacao(LocalDate.now());
        obj2.setSaldo(obj2.getSaldo().add(obj1.getSaldo()));

        obj1.setDataAtualizacao(LocalDate.now());
        obj1.setSaldo(new BigDecimal(0));

        contaRepository.save(obj1);
        contaRepository.save(obj2);

        return new ResponseEntity<>(contaRepository.save(obj2), HttpStatus.OK);
    }

    //Excluindo o registro
    public ResponseEntity<ObjectDto> excluir(Long id) throws ServiceException {
        contaRepository.deleteById(id);
        ObjectDto exclusaoDto = new ObjectDto();
        exclusaoDto.setId(id);
        exclusaoDto.setMensagem("Conta: Registro exluído com sucesso");
        return new ResponseEntity<ObjectDto>(exclusaoDto, HttpStatus.OK);
    }

    private Pessoa getPessoa(Long id) {
        return pessoaRepository.findById(id).get();
    }
}
