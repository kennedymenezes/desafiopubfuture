package br.com.desafiopubfuture.repository;

import br.com.desafiopubfuture.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Pessoa findByDocumento(String documento);
}
