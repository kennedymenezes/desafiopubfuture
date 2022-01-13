package br.com.desafiopubfuture.service;

import br.com.desafiopubfuture.dto.ObjectDto;
import br.com.desafiopubfuture.dto.PessoaDto;
import br.com.desafiopubfuture.model.Pessoa;
import br.com.desafiopubfuture.repository.PessoaRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PessoaService extends BaseService {

    @Autowired
    private PessoaRepository pessoaRepository;

    //Pesquisa em lista
    public ResponseEntity<List<Pessoa>> getListaPessoas() throws ServiceException {
        return new ResponseEntity<>(pessoaRepository.findAll(), HttpStatus.OK);
    }

    //Pesquisa por ID
    public ResponseEntity<Pessoa> getPessoaId(Long id) {
        return new ResponseEntity<Pessoa>(pessoaRepository.findById(id).get(), HttpStatus.OK);
    }

    //Pesquisa por DOCUMENTO
    public ResponseEntity<Pessoa> getPessoaDocumento(String documento) {
        return new ResponseEntity<>(pessoaRepository.findByDocumento(documento), HttpStatus.OK);
    }

    //Salvando o registro criando
    public ResponseEntity<Pessoa> salvar(PessoaDto objDto) throws ServiceException {
        Pessoa obj = modelMapper.map(objDto, Pessoa.class);
        obj.setDataCadastro(LocalDate.now());
        obj.setDataAtualizacao(LocalDate.now());
        return new ResponseEntity<Pessoa>(pessoaRepository.save(obj), HttpStatus.OK);
    }

    //Salvando o registro atualizando
    public ResponseEntity<Pessoa> atualizar(Long id, PessoaDto objDto) throws ServiceException {
        Pessoa obj = pessoaRepository.findById(id).get();
        obj.setDataAtualizacao(LocalDate.now());
        obj.setDocumento(obj.getDocumento());
        obj.setEmail(objDto.getEmail());
        obj.setNome(objDto.getNome());
        return new ResponseEntity<Pessoa>(pessoaRepository.save(obj), HttpStatus.OK);
    }

    //Salvando o registro atualizando somente nome e email
    public ResponseEntity<Pessoa> atualizarNomeEmail(Long id, String nome, String email) throws ServiceException {
        Pessoa obj = pessoaRepository.findById(id).get();
        obj.setDataAtualizacao(LocalDate.now());
        obj.setEmail(email);
        obj.setNome(nome);
        return new ResponseEntity<Pessoa>(pessoaRepository.save(obj), HttpStatus.OK);
    }

    //Excluindo o registro
    public ResponseEntity<ObjectDto> excluir(Long id) throws ServiceException {
        pessoaRepository.deleteById(id);
        ObjectDto exclusaoDto = new ObjectDto();
        exclusaoDto.setId(id);
        exclusaoDto.setMensagem("Pessoa: Registro exluído com sucesso");
        return new ResponseEntity<ObjectDto>(exclusaoDto, HttpStatus.OK);
    }
}
