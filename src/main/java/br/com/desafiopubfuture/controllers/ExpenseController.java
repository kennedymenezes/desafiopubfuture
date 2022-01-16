package br.com.desafiopubfuture.controllers;

import br.com.desafiopubfuture.dto.ExpenseDto;
import br.com.desafiopubfuture.dto.ObjectDto;
import br.com.desafiopubfuture.enums.ExpenseType;
import br.com.desafiopubfuture.model.Expense;
import br.com.desafiopubfuture.service.ExpenseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "despesas")
public class ExpenseController {

    @Autowired
    private ExpenseService despesaService;

    @ApiOperation(value = "Returns a list of expenses")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns a list of expenses"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @GetMapping(value = "/getAll", produces = "application/json")
    public ResponseEntity<List<Expense>> getAll() {
        return despesaService.getAll();
    }

    @ApiOperation(value = "Returns a list of expenses of a given type")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns a list of expenses of a given type"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @GetMapping(value = "/getAllExpenseType/{expenseType}", produces = "application/json")
    public ResponseEntity<List<Expense>> getAllExpenseType(@PathVariable ExpenseType expenseType) {
        return despesaService.getAllExpenseType(expenseType);
    }

    @ApiOperation(value = "Returns a list of expenses that contain the description")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns a list of expenses that contain the description"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @GetMapping(value = "/getAllDescriptionContais/{description}", produces = "application/json")
    public ResponseEntity<List<Expense>> getAllDescriptionContais(String description) {
        return despesaService.getAllDescriptionContais(description);
    }

    @ApiOperation(value = "Returns the sum of all expenses")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the sum of all expenses"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @GetMapping(value = "/getExpenseSum", produces = "application/json")
    public ResponseEntity<BigDecimal> getExpenseSum() {
        return despesaService.getExpenseSum();
    }

    @ApiOperation(value = "Returns expenses by payment date of a period")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns expenses by payment date of a period"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @GetMapping(value = "/getAllBetweenDates/{start}/{end}", produces = "application/json")
    public ResponseEntity<List<Expense>> getAllBetweenDates(LocalDate start, LocalDate end) {
        return despesaService.getAllBetweenDates(start, end);
    }

    @ApiOperation(value = "Change the date and amount of the expense")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns expenses with the changed values"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @PatchMapping(value = "/update/{id}/{amout}/{paymentDate}", produces = "application/json")
    public ResponseEntity<Expense> update(Long id, BigDecimal amout, LocalDate paymentDate) {
        return despesaService.update(id, amout, paymentDate);
    }


    @ApiOperation(value = "Returns an expense using the ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns an expense using the ID"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @GetMapping(value = "/getById/{id}", produces = "application/json")
    public ResponseEntity<Expense> getById(@PathVariable Long id) {
        return despesaService.getById(id);
    }

    @ApiOperation(value = "Save expense data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the expense data saved in the database"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @PostMapping(value = "/save", produces = "application/json")
    public ResponseEntity<Expense> save(@Validated @RequestBody ExpenseDto expenseDto) {
        return despesaService.save(expenseDto);
    }

    @ApiOperation(value = "Update expense data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the updated expense data in the database"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @PutMapping(value = "/update/{id}", produces = "application/json")
    public ResponseEntity<Expense> update(@Validated @RequestBody ExpenseDto expenseDto, @PathVariable Long id) {
        return despesaService.update(id, expenseDto);
    }

    @ApiOperation(value = "Delete an expense using the ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully delete expense"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @DeleteMapping(value = "/deleteRecord/{id}", produces = "application/json")
    public ResponseEntity<ObjectDto> deleteRecord(@PathVariable Long id) {
        return despesaService.deleteRecord(id);
    }
}
