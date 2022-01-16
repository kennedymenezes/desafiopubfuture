package br.com.desafiopubfuture.controllers;

import br.com.desafiopubfuture.dto.ObjectDto;
import br.com.desafiopubfuture.dto.PeopleDto;
import br.com.desafiopubfuture.model.People;
import br.com.desafiopubfuture.service.PeopleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "pessoas")
public class PeopleController {

    @Autowired
    private PeopleService pessoaService;

    @ApiOperation(value = "Returns a list of people")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns a list of people"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @GetMapping(value = "getAll", produces = "application/json")
    public ResponseEntity<List<People>> getAll() {
        return pessoaService.getAll();
    }

    @ApiOperation(value = "Returns a person using the ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns a person"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @GetMapping(value = "getById/{id}", produces = "application/json")
    public ResponseEntity<People> getById(@PathVariable Long id) {
        return pessoaService.getById(id);
    }

    @ApiOperation(value = "Returns a person using the registered document")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns a person"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @GetMapping(value = "getByDocument/{document}", produces = "application/json")
    public ResponseEntity<People> getByDocument(@PathVariable String document) {
        return pessoaService.getByDocument(document);
    }

    @ApiOperation(value = "Save person data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the data of the person saved in the database"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @PostMapping(value = "save", produces = "application/json")
    public ResponseEntity<People> save(@Validated @RequestBody PeopleDto peopleDto) {
        return pessoaService.save(peopleDto);
    }

    @ApiOperation(value = "Update person data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message =
                    "Returns the updated person data in the database"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @PutMapping(value = "update/{id}", produces = "application/json")
    public ResponseEntity<People> update(@Validated @RequestBody PeopleDto pessoa, @PathVariable Long id) {
        return pessoaService.update(id, pessoa);
    }

    @ApiOperation(value = "Updates only the person's name and email")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the updated person data in the database"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @PatchMapping(value = "update/{id}/{name}/{email}", produces = "application/json")
    public ResponseEntity<People> update(@PathVariable Long id, @PathVariable String nome, @PathVariable String email) {
        return pessoaService.update(id, nome, email);
    }

    @ApiOperation(value = "Delete a person using the ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleting successfully person"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @DeleteMapping(value = "deleteRecord/{id}", produces = "application/json")
    public ResponseEntity<ObjectDto> deleteRecord(@PathVariable Long id) {
        return pessoaService.deleteRecord(id);
    }
}
