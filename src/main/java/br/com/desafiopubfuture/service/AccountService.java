package br.com.desafiopubfuture.service;

import br.com.desafiopubfuture.dto.AccountDto;
import br.com.desafiopubfuture.dto.ObjectDto;
import br.com.desafiopubfuture.model.Account;
import br.com.desafiopubfuture.model.People;
import br.com.desafiopubfuture.repository.AccountRepository;
import br.com.desafiopubfuture.repository.PeopleRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class AccountService extends BaseService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PeopleRepository peopleRepository;

    //Pesquisa em lista
    public ResponseEntity<List<Account>> getAll() throws ServiceException {
        return new ResponseEntity<>(accountRepository.findAll(), HttpStatus.OK);
    }

    //Pesquisa de 1 único resultado por um campo unico
    public ResponseEntity<Account> getById(Long id) {
        return new ResponseEntity<Account>(accountRepository.findById(id).get(), HttpStatus.OK);
    }

    public Account getObjectAccountObject(Long id) {
        return accountRepository.findById(id).get();
    }

    //Salvando o registro (criando e atualizando
    public ResponseEntity<Account> save(AccountDto accountDto) throws ServiceException {
        Account account = modelMapper.map(accountDto, Account.class);
        account.setRegistrationDate(LocalDate.now());
        account.setUpdateDate(LocalDate.now());
        account.setPeople(getPeople(accountDto.getPeopleId()));

        return new ResponseEntity<>(accountRepository.save(account), HttpStatus.OK);
    }

    //Salvando o registro atualizando
    public ResponseEntity<Account> update(Long id, AccountDto accountDto) throws ServiceException {
        Account account = accountRepository.findById(id).get();
        account.setUpdateDate(LocalDate.now());
        account.setChekingAccount(accountDto.getChekingAccount());
        account.setAgency(accountDto.getAgency());
        account.setFinancialInstitutionName(accountDto.getFinancialInstitutionName());
        account.setAccountType(accountDto.getAccountType());
        account.setAmount(accountDto.getAmount());

        account.setPeople(getPeople(accountDto.getPeopleId()));

        return new ResponseEntity<>(accountRepository.save(account), HttpStatus.OK);
    }

    //Atualizar somente o saldo
    public ResponseEntity<Account> update(Long id, BigDecimal amount) throws ServiceException {
        Account account = accountRepository.findById(id).get();
        account.setUpdateDate(LocalDate.now());
        account.setAmount(amount);

        return new ResponseEntity<>(accountRepository.save(account), HttpStatus.OK);
    }

    //Transfere o saldo entre contas
    public ResponseEntity<Account> amountTransfer(Long originId, Long destinyId) throws ServiceException {
        Account originAccount = accountRepository.findById(originId).get();
        Account destinyAccount = accountRepository.findById(destinyId).get();

        destinyAccount.setUpdateDate(LocalDate.now());
        destinyAccount.setAmount(destinyAccount.getAmount().add(originAccount.getAmount()));

        originAccount.setUpdateDate(LocalDate.now());
        originAccount.setAmount(new BigDecimal(0));

        accountRepository.save(originAccount);
        accountRepository.save(destinyAccount);

        return new ResponseEntity<>(accountRepository.save(destinyAccount), HttpStatus.OK);
    }

    //Excluindo o registro
    public ResponseEntity<ObjectDto> deleteRecord(Long id) throws ServiceException {
        accountRepository.deleteById(id);
        ObjectDto dtoObject = new ObjectDto();
        dtoObject.setId(id);
        dtoObject.setMessage("Conta: Registro exluído com sucesso");
        return new ResponseEntity<ObjectDto>(dtoObject, HttpStatus.OK);
    }

    private People getPeople(Long id) {
        return peopleRepository.findById(id).get();
    }
}
