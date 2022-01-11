package br.com.desafiopubfuture.service;
/***********************************************************************************************************************
 * Classe responsavel por trabalhar com a tabela Pessoas
 * - Localizar por ID - 1 objeto
 * - Localizar por Documento - 1 Objeto
 * - Localizar todos
 * - Salvar uma pessoa
 * - Atualizar pessoa
 * *Campos de data
 ***********************************************************************************************************************/

import br.com.desafiopubfuture.dto.PessoaDto;
import br.com.desafiopubfuture.model.Pessoa;
import br.com.desafiopubfuture.repository.PessoaRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PessoaService extends BaseService {

    @Autowired
    private PessoaRepository pessoaRepository;

    //Pesquisa em lista
    public ResponseEntity<Iterable<Pessoa>> getListaPessoas() throws ServiceException {
        return new ResponseEntity<>(pessoaRepository.findAll(), HttpStatus.OK);
    }

    //Pesquisa de 1 único resultado por um campo unico
    public ResponseEntity<Pessoa> getPessoa(Integer id, String documento) {
        if (!documento.trim().equals(""))
            return new ResponseEntity<>(pessoaRepository.findByDocumento(documento), HttpStatus.OK);

        return new ResponseEntity<>(pessoaRepository.findById(id), HttpStatus.OK);

    }

    //Salvando o registro criando
    public ResponseEntity<Pessoa> salvar(PessoaDto objDto) throws ServiceException {
        Pessoa obj = modelMapper.map(objDto, Pessoa.class);
        obj.setDataCadastro(new Date());
        return new ResponseEntity<>(pessoaRepository.save(obj), HttpStatus.OK);
    }

    //Salvando o registro atualizando
    public ResponseEntity<Pessoa> atualizar(Integer id, PessoaDto objDto) throws ServiceException {
        Pessoa obj = pessoaRepository.findById(id);
        obj.setDataAtualizacao(new Date());
        obj.setEmail(objDto.getEmail());
        obj.setNome(objDto.getNome());

        return new ResponseEntity<>(pessoaRepository.save(obj), HttpStatus.OK);
    }

    //Excluindo o registro
    public ResponseEntity<Void> excluir(Integer id) throws ServiceException {
        pessoaRepository.deleteById(id.longValue());
        return ResponseEntity.noContent().build();
    }
}
