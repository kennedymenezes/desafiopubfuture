package br.com.desafiopubfuture.repository;

import br.com.desafiopubfuture.model.Conta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends CrudRepository<Conta, Long> {
    Conta findById(Integer id);
}
