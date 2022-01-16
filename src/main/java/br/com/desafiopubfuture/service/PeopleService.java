package br.com.desafiopubfuture.service;

import br.com.desafiopubfuture.dto.ObjectDto;
import br.com.desafiopubfuture.dto.PeopleDto;
import br.com.desafiopubfuture.model.People;
import br.com.desafiopubfuture.repository.PeopleRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PeopleService extends BaseService {

    @Autowired
    private PeopleRepository pessoaRepository;

    //Pesquisa em lista
    public ResponseEntity<List<People>> getAll() throws ServiceException {
        return new ResponseEntity<>(pessoaRepository.findAll(), HttpStatus.OK);
    }

    //Pesquisa por ID
    public ResponseEntity<People> getById(Long id) {
        return new ResponseEntity<People>(pessoaRepository.findById(id).get(), HttpStatus.OK);
    }

    //Pesquisa por DOCUMENTO
    public ResponseEntity<People> getByDocument(String documento) {
        return new ResponseEntity<>(pessoaRepository.findByDocument(documento), HttpStatus.OK);
    }

    //Salvando o registro criando
    public ResponseEntity<People> save(PeopleDto objDto) throws ServiceException {
        People obj = modelMapper.map(objDto, People.class);
        obj.setRegistrationDate(LocalDate.now());
        obj.setUpdateDate(LocalDate.now());
        return new ResponseEntity<People>(pessoaRepository.save(obj), HttpStatus.OK);
    }

    //Salvando o registro atualizando
    public ResponseEntity<People> update(Long id, PeopleDto peopleDto) throws ServiceException {
        People obj = pessoaRepository.findById(id).get();
        obj.setUpdateDate(LocalDate.now());
        obj.setDocument(peopleDto.getDocument());
        obj.setEmail(peopleDto.getEmail());
        obj.setName(peopleDto.getName());
        return new ResponseEntity<People>(pessoaRepository.save(obj), HttpStatus.OK);
    }

    //Salvando o registro atualizando somente nome e email
    public ResponseEntity<People> update(Long id, String nome, String email) throws ServiceException {
        People obj = pessoaRepository.findById(id).get();
        obj.setUpdateDate(LocalDate.now());
        obj.setEmail(email);
        obj.setName(nome);
        return new ResponseEntity<People>(pessoaRepository.save(obj), HttpStatus.OK);
    }

    //Excluindo o registro
    public ResponseEntity<ObjectDto> deleteRecord(Long id) throws ServiceException {
        pessoaRepository.deleteById(id);
        ObjectDto objectDto = new ObjectDto();
        objectDto.setId(id);
        objectDto.setMessage("Pessoa: Registro exluído com sucesso");
        return new ResponseEntity<ObjectDto>(objectDto, HttpStatus.OK);
    }
}
