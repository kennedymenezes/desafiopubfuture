package br.com.desafiopubfuture.repository;

import br.com.desafiopubfuture.enums.ExpenseType;
import br.com.desafiopubfuture.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByDescriptionContaining(String description);

    List<Expense> findByExpenseType(ExpenseType expenseType);

    @Query(value = "SELECT * FROM expense  WHERE paymente_date BETWEEN :? AND :? ORDER BY paymente_date DESC", nativeQuery = true)
    List<Expense> findByDataPagamentoBetween(LocalDate start, LocalDate end);


    @Query(value = "SELECT SUM(amunt) FROM expense", nativeQuery = true)
    BigDecimal expenseSum();


}
