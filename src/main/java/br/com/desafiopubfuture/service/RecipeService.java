package br.com.desafiopubfuture.service;

import br.com.desafiopubfuture.dto.ObjectDto;
import br.com.desafiopubfuture.dto.RecipeDto;
import br.com.desafiopubfuture.enums.RecipeType;
import br.com.desafiopubfuture.model.Recipe;
import br.com.desafiopubfuture.repository.RecipeRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class RecipeService extends BaseService {

    @Autowired
    private RecipeRepository reipeRepository;

    @Autowired
    private AccountService accountService;

    //Pesquisa em lista
    public ResponseEntity<List<Recipe>> getAll() throws ServiceException {
        return new ResponseEntity<>(reipeRepository.findAll(), HttpStatus.OK);
    }

    //Pesquisa em lista
    public ResponseEntity<List<Recipe>> getAllByRecipeType(RecipeType recipeType) throws ServiceException {
        return new ResponseEntity<List<Recipe>>(reipeRepository.findByRecipeType(recipeType), HttpStatus.OK);
    }

    //Pesquisa em lista
    public ResponseEntity<List<Recipe>> getAllByDescritionConstains(String description) throws ServiceException {
        return new ResponseEntity<List<Recipe>>(reipeRepository.findByDescriptionContaining(description), HttpStatus.OK);
    }

    //Pesquisa em lista
    public ResponseEntity<List<Recipe>> getAllBetweenDataRecebimento(LocalDate start, LocalDate end) throws ServiceException {
        return new ResponseEntity<List<Recipe>>(reipeRepository.findByReceivingDate(start, end), HttpStatus.OK);
    }

    //Pesquisa de 1 único resultado por um campo unico
    public ResponseEntity<Recipe> getById(Long id) {
        return new ResponseEntity<Recipe>(reipeRepository.findById(id).get(), HttpStatus.OK);
    }

    //Obtem o total de despesas
    public ResponseEntity<BigDecimal> getRecipeSum() throws ServiceException {
        return new ResponseEntity<>(reipeRepository.recipeSum(), HttpStatus.OK);
    }

    //Salvando o registro criando
    public ResponseEntity<Recipe> save(RecipeDto recipeDto) throws ServiceException {
        Recipe recipe = modelMapper.map(recipeDto, Recipe.class);
        recipe.setRegistrationDate(LocalDate.now());
        recipe.setUpdateDate(LocalDate.now());
        return new ResponseEntity<>(reipeRepository.save(recipe), HttpStatus.OK);
    }

    //Salvando o registro atualizando
    public ResponseEntity<Recipe> update(Long id, RecipeDto recipeDto) throws ServiceException {
        Recipe recipe = reipeRepository.findById(id).get();
        recipe.setUpdateDate(LocalDate.now());
        recipe.setAmount(recipeDto.getAmount());

        recipe.setRecipeType(recipeDto.getTypeRecipe());
        recipe.setExpectedReceivingDate(recipeDto.getExpectedReceivingDate());
        recipe.setDescription(recipeDto.getDescription());
        if (recipeDto.getReceivingDate() != null) {
            recipe.setReceivingDate(recipeDto.getReceivingDate());
            recipe.setAmountRecipe(recipeDto.getAmountReceived());
            recipe.setAccount(accountService.getObjectAccountObject(recipeDto.getAccountId()));
        }

        return new ResponseEntity<>(reipeRepository.save(recipe), HttpStatus.OK);
    }

    //Salvando o registro atualizando
    public ResponseEntity<Recipe> update(Long id, BigDecimal amount, LocalDate recipeDate) throws ServiceException {
        Recipe recipe = reipeRepository.findById(id).get();

        //Subtraindo o valor antigo do recebimento
        accountService.update(recipe.getAccount().getId(), (recipe.getAmount().multiply(new BigDecimal(-1))));

        recipe.setUpdateDate(LocalDate.now());
        recipe.setAmount(amount);
        recipe.setReceivingDate(recipeDate);

        //Adicionando o valor do recebimento atualizado
        accountService.update(recipe.getAccount().getId(), (recipe.getAmount().add(amount)));

        return new ResponseEntity<>(reipeRepository.save(recipe), HttpStatus.OK);
    }

    //Excluindo o registro
    public ResponseEntity<ObjectDto> deleteRecord(Long id) throws ServiceException {
        reipeRepository.deleteById(id);
        ObjectDto objectDto = new ObjectDto();
        objectDto.setId(id);
        objectDto.setMessage("Receita: Registro exluído com sucesso");
        return new ResponseEntity<ObjectDto>(objectDto, HttpStatus.OK);
    }

}
