package br.com.desafiopubfuture.repository;

import br.com.desafiopubfuture.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {
    People findByDocument(String document);
}
