package com.algusto.algusto.repository;

import com.algusto.algusto.entity.Ingredient;
import com.algusto.algusto.entity.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    private Ingredient eggs;
    private Ingredient butter;
    private Ingredient milk;

    @BeforeEach
    void setUp() {
        eggs = new Ingredient();
        eggs.setName("eggs");
        eggs = ingredientRepository.save(eggs);

        butter = new Ingredient();
        butter.setName("butter");
        butter = ingredientRepository.save(butter);

        milk = new Ingredient();
        milk.setName("milk");
        milk = ingredientRepository.save(milk);
    }

    @Test
    void findByIngredientNames_returnsRecipeWhenAllIngredientsMatch() {
        Recipe scrambledEggs = new Recipe();
        scrambledEggs.setName("Scrambled Eggs");
        scrambledEggs.setIngredients(List.of(eggs, butter));
        recipeRepository.save(scrambledEggs);

        List<Recipe> results = recipeRepository.findByIngredientNames(
                List.of("eggs", "butter")
        );

        assertEquals(1, results.size());
        assertEquals("Scrambled Eggs", results.getFirst().getName());
    }

    @Test
    void findByIngredientNames_returnsRecipeWhenInputIsSuperset() {
        Recipe scrambledEggs = new Recipe();
        scrambledEggs.setName("Scrambled Eggs");
        scrambledEggs.setIngredients(List.of(eggs, butter));
        recipeRepository.save(scrambledEggs);

        List<Recipe> results = recipeRepository.findByIngredientNames(
                List.of("eggs", "butter", "milk")
        );

        assertEquals(1, results.size());
    }

    @Test
    void findByIngredientNames_doesNotReturnRecipeWhenInputIsSubset() {
        Recipe scrambledEggs = new Recipe();
        scrambledEggs.setName("Scrambled Eggs");
        scrambledEggs.setIngredients(List.of(eggs, butter));
        recipeRepository.save(scrambledEggs);

        List<Recipe> results = recipeRepository.findByIngredientNames(
                List.of("eggs")
        );

        assertTrue(results.isEmpty());
    }

    @Test
    void findByIngredientNames_isCaseInsensitiveOnStoredData() {
        Ingredient mixedCaseEggs = new Ingredient();
        mixedCaseEggs.setName("Eggs");
        mixedCaseEggs = ingredientRepository.save(mixedCaseEggs);

        Recipe scrambledEggs = new Recipe();
        scrambledEggs.setName("Scrambled Eggs");
        scrambledEggs.setIngredients(List.of(mixedCaseEggs));
        recipeRepository.save(scrambledEggs);

        List<Recipe> results = recipeRepository.findByIngredientNames(
                List.of("eggs")
        );

        assertEquals(1, results.size());
    }

    @Test
    void findByIngredientNames_doesNotReturnRecipeWithNoIngredients() {
        Recipe empty = new Recipe();
        empty.setName("Empty Recipe");
        recipeRepository.save(empty);

        List<Recipe> results = recipeRepository.findByIngredientNames(
                List.of("eggs")
        );

        assertTrue(results.isEmpty());
    }

    @Test
    void findByIngredientNames_returnsOnlyMatchingRecipes() {
        Recipe scrambledEggs = new Recipe();
        scrambledEggs.setName("Scrambled Eggs");
        scrambledEggs.setIngredients(List.of(eggs));
        recipeRepository.save(scrambledEggs);

        Recipe milkshake = new Recipe();
        milkshake.setName("Milkshake");
        milkshake.setIngredients(List.of(milk));
        recipeRepository.save(milkshake);

        List<Recipe> results = recipeRepository.findByIngredientNames(
                List.of("eggs")
        );

        assertEquals(1, results.size());
        assertEquals("Scrambled Eggs", results.getFirst().getName());
    }

    @Test
    void findByIngredientNames_handlesIngredientWithNullName() {
        Ingredient nullName = new Ingredient();
        nullName = ingredientRepository.save(nullName);

        Recipe recipe = new Recipe();
        recipe.setName("Mystery Recipe");
        recipe.setIngredients(List.of(nullName));
        recipeRepository.save(recipe);

        List<Recipe> results = recipeRepository.findByIngredientNames(List.of("eggs"));

        assertTrue(results.isEmpty());
    }
}
