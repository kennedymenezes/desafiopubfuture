package br.com.desafiopubfuture.controllers;


import br.com.desafiopubfuture.dto.ObjectDto;
import br.com.desafiopubfuture.dto.RecipeDto;
import br.com.desafiopubfuture.enums.RecipeType;
import br.com.desafiopubfuture.model.Recipe;
import br.com.desafiopubfuture.service.RecipeService;
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
@RequestMapping(value = "receitas")
public class RecipeController {

    @Autowired
    private RecipeService receitaService;

    @ApiOperation(value = "Returns a list of recipes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns a list of recipes"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @GetMapping(value = "getAll", produces = "application/json")
    public ResponseEntity<List<Recipe>> getAll() {
        return receitaService.getAll();
    }

    @ApiOperation(value = "Returns a recipe using the ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns a recipe using the ID"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @GetMapping(value = "getById/{id}", produces = "application/json")
    public ResponseEntity<Recipe> getById(@PathVariable Long id) {
        return receitaService.getById(id);
    }

    @ApiOperation(value = "Returns a list of recipes that contain the description")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns a list of recipes that contain the description"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @GetMapping(value = "getAllByDescritionConstains/{description}", produces = "application/json")
    public ResponseEntity<List<Recipe>> getAllByDescritionConstains(String description) {
        return receitaService.getAllByDescritionConstains(description);
    }

    @ApiOperation(value = "Returns a list of recipes that contain the type of recipe")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns a list of recipes that contain the type of recipe"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @GetMapping(value = "getAllByRecipeType/{recipeType}", produces = "application/json")
    public ResponseEntity<List<Recipe>> getAllByRecipeType(RecipeType recipeType) {
        return receitaService.getAllByRecipeType(recipeType);
    }

    @ApiOperation(value = "Returns expenses by receipt date of a period")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns expenses by receipt date of a period"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @GetMapping(value = "getAllBetweenDataRecebimento/{start}/{end}", produces = "application/json")
    public ResponseEntity<List<Recipe>> getAllBetweenDataRecebimento(LocalDate start, LocalDate end) {
        return receitaService.getAllBetweenDataRecebimento(start, end);
    }

    @ApiOperation(value = "Returns the sum of total revenues")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the sum of total revenues"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @GetMapping(value = "/getRecipeSum", produces = "application/json")
    public ResponseEntity<BigDecimal> getRecipeSum() {
        return receitaService.getRecipeSum();
    }

    @ApiOperation(value = "Save recipe data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the recipe data saved in the database"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Recipe> save(@Validated @RequestBody RecipeDto recipeDto) {
        return receitaService.save(recipeDto);
    }

    @ApiOperation(value = "Change the date and amount of revenue")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the updated revenue data in the database"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @PatchMapping(value = "/update/{id}/{amount}/{recipeDate}", produces = "application/json")
    public ResponseEntity<Recipe> update(Long id, BigDecimal amount, LocalDate recipeDate) {
        return receitaService.update(id, amount, recipeDate);
    }

    @ApiOperation(value = "Update revenue data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the updated revenue data in the database"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @PutMapping(value = "/update/{id}", produces = "application/json")
    public ResponseEntity<Recipe> update(@Validated @RequestBody RecipeDto recipeDto, @PathVariable Long id) {
        return receitaService.update(id, recipeDto);
    }

    @ApiOperation(value = "Delete a recipe using the ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleting successfully person"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @DeleteMapping(value = "deleteRecord/{id}", produces = "application/json")
    public ResponseEntity<ObjectDto> deleteRecord(@PathVariable Long id) {
        return receitaService.deleteRecord(id);
    }
}
