package br.com.desafiopubfuture.repository;

import br.com.desafiopubfuture.model.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
    Pessoa findById(Integer id);

    Pessoa findByDocumento(String documento);
}
