package br.com.desafiopubfuture.controllers;

import br.com.desafiopubfuture.dto.AccountDto;
import br.com.desafiopubfuture.dto.ObjectDto;
import br.com.desafiopubfuture.model.Account;
import br.com.desafiopubfuture.service.AccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "conta")
public class AccountController {

    @Autowired
    private AccountService accoutService;

    @ApiOperation(value = "Returns an account list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns an account list"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @GetMapping(value = "getAll", produces = "application/json")
    public ResponseEntity<List<Account>> getAll() {
        return accoutService.getAll();
    }

    @ApiOperation(value = "Returns an account using the ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns an account using the ID"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @GetMapping(value = "getById/{id}", produces = "application/json")
    public ResponseEntity<Account> getById(@PathVariable Long id) {
        return accoutService.getById(id);
    }

    @ApiOperation(value = "Save account data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the account data saved in the bank"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @PostMapping(value = "save", produces = "application/json")
    public ResponseEntity<Account> save(@Validated @RequestBody AccountDto accountDto) {
        return accoutService.save(accountDto);
    }

    @ApiOperation(value = "Update account data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the updated account data in the database"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @PutMapping(value = "update/{id}", produces = "application/json")
    public ResponseEntity<Account> update(@Validated @RequestBody AccountDto accountDto, @PathVariable Long id) {
        return accoutService.update(id, accountDto);
    }


    @ApiOperation(value = "Balance transfer between accounts")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the updated balance in the target account"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @PatchMapping(value = "amountTransfer/{originId}/{destinyId}", produces = "application/json")
    public ResponseEntity<Account> amountTransfer(@PathVariable Long originId, @PathVariable Long destinyId) {
        return accoutService.amountTransfer(originId, destinyId);
    }

    @ApiOperation(value = "Update account balance")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the updated bank account data"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @PatchMapping(value = "update/{id}/{amount}", produces = "application/json")
    public ResponseEntity<Account> update(@PathVariable Long id, @PathVariable BigDecimal amount) {
        return accoutService.update(id, amount);
    }

    @ApiOperation(value = "Delete an account using the ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully delete account"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @DeleteMapping(value = "deleteRecord/{id}", produces = "application/json")
    public ResponseEntity<ObjectDto> deleteRecord(@PathVariable Long id) {
        return accoutService.deleteRecord(id);
    }
}
