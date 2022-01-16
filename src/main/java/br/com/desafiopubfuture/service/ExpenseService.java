package br.com.desafiopubfuture.service;

import br.com.desafiopubfuture.dto.ExpenseDto;
import br.com.desafiopubfuture.dto.ObjectDto;
import br.com.desafiopubfuture.enums.ExpenseType;
import br.com.desafiopubfuture.model.Expense;
import br.com.desafiopubfuture.repository.ExpenseRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService extends BaseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private AccountService accountService;

    //Pesquisa em lista
    public ResponseEntity<List<Expense>> getAll() throws ServiceException {
        return new ResponseEntity<>(expenseRepository.findAll(), HttpStatus.OK);
    }

    //Pesquisa em lista contendo a descricao
    public ResponseEntity<List<Expense>> getAllDescriptionContais(String description) throws ServiceException {
        return new ResponseEntity<List<Expense>>(expenseRepository.findByDescriptionContaining(description), HttpStatus.OK);
    }

    //Pesquisa em lista contendo a descricao
    public ResponseEntity<List<Expense>> getAllExpenseType(ExpenseType expenseType) throws ServiceException {
        return new ResponseEntity<List<Expense>>(expenseRepository.findByExpenseType(expenseType), HttpStatus.OK);
    }

    //Pesquisa em lista pelo intervado de datas de pagamento
    public ResponseEntity<List<Expense>> getAllBetweenDates(LocalDate start, LocalDate end) throws ServiceException {
        return new ResponseEntity<>(expenseRepository.findByDataPagamentoBetween(start, end), HttpStatus.OK);
    }

    //Obtem o total de despesas
    public ResponseEntity<BigDecimal> getExpenseSum() throws ServiceException {
        return new ResponseEntity<>(expenseRepository.expenseSum(), HttpStatus.OK);
    }

    //Pesquisa de 1 único resultado por um campo unico
    public ResponseEntity<Expense> getById(Long id) {
        return new ResponseEntity<Expense>(expenseRepository.findById(id).get(), HttpStatus.OK);
    }

    //Salvando o registro criando
    public ResponseEntity<Expense> save(ExpenseDto expenseDto) throws ServiceException {
        Expense expense = modelMapper.map(expenseDto, Expense.class);
        expense.setRegistrationDate(LocalDate.now());
        expense.setUpdateDate(LocalDate.now());
        expense.setAccount(accountService.getObjectAccountObject(expenseDto.getAccountId()));

        return new ResponseEntity<>(expenseRepository.save(expense), HttpStatus.OK);
    }

    //Salvando o registro atualizando
    public ResponseEntity<Expense> update(Long id, ExpenseDto expenseDto) throws ServiceException {
        Expense expense = expenseRepository.findById(id).get();
        expense.setUpdateDate(LocalDate.now());

        expense.setAmount(expenseDto.getAmount());
        expense.setExpectedPaymentDate(expenseDto.getExpectedPaymentDate());
        expense.setPaymenteDate(expenseDto.getPaymenteDate());
        expense.setExpenseType(expenseDto.getExpenseType());
        expense.setDescription(expenseDto.getDescription());
        expense.setAccount(accountService.getObjectAccountObject(expenseDto.getAccountId()));

        return new ResponseEntity<>(expenseRepository.save(expense), HttpStatus.OK);
    }

    //Atualizando somente a valor e data
    public ResponseEntity<Expense> update(Long id, BigDecimal amount, LocalDate paymentDate) throws ServiceException {
        Expense expense = expenseRepository.findById(id).get();
        expense.setUpdateDate(LocalDate.now());

        expense.setAmount(amount);
        expense.setPaymenteDate(paymentDate);

        accountService.update(expense.getAccount().getId(), expense.getAccount().getAmount().min(amount));

        return new ResponseEntity<>(expenseRepository.save(expense), HttpStatus.OK);
    }

    //Excluindo o registro
    public ResponseEntity<ObjectDto> deleteRecord(Long id) throws ServiceException {
        expenseRepository.deleteById(id);
        ObjectDto expenseectDto = new ObjectDto();
        expenseectDto.setId(id);
        expenseectDto.setMessage("Despesa: Registro exluído com sucesso");
        return new ResponseEntity<ObjectDto>(expenseectDto, HttpStatus.OK);
    }

}
