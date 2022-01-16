package br.com.desafiopubfuture.repository;

import br.com.desafiopubfuture.enums.RecipeType;
import br.com.desafiopubfuture.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query(value = "SELECT * FROM Recipe  WHERE receiving_date BETWEEN :? AND :? ORDER BY receiving_date DESC", nativeQuery = true)
    List<Recipe> findByReceivingDate(LocalDate start, LocalDate end);

    @Query(value = "SELECT SUM(amount) FROM Recipe")
    BigDecimal recipeSum();

    List<Recipe> findByDescriptionContaining(String descricao);

    List<Recipe> findByRecipeType(RecipeType recipeType);
}
