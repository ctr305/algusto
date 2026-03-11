package com.algusto.algusto.service;

import com.algusto.algusto.entity.Ingredient;
import com.algusto.algusto.entity.Recipe;
import com.algusto.algusto.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeService recipeService;

    @Test
    void testFindAllRecipes() {
        Ingredient eggs = new Ingredient();
        eggs.setName("eggs");

        Ingredient milk = new Ingredient();
        milk.setName("milk");

        Recipe scrambledEggs = new Recipe();
        scrambledEggs.setName("Scrambled Eggs");
        scrambledEggs.setIngredients(List.of(eggs));

        Recipe milkshake = new Recipe();
        milkshake.setName("Milkshake");
        milkshake.setIngredients(List.of(milk));

        when(recipeRepository.findAll()).thenReturn(List.of(scrambledEggs, milkshake));

        List<Recipe> results = recipeService.getAllRecipes();

        assertEquals(2, results.size());
        assertEquals(scrambledEggs.getName(), results.get(0).getName());
        assertEquals(milkshake.getName(), results.get(1).getName());
    }

    @Test
    void testFindRecipesByIngredients() {
        Ingredient eggs = new Ingredient();
        eggs.setName("eggs");

        Ingredient milk = new Ingredient();
        milk.setName("milk");

        Recipe scrambledEggs = new Recipe();
        scrambledEggs.setName("Scrambled Eggs");
        scrambledEggs.setIngredients(List.of(eggs));

        Recipe milkshake = new Recipe();
        milkshake.setName("Milkshake");
        milkshake.setIngredients(List.of(milk));

        when(recipeRepository.findAll()).thenReturn(List.of(scrambledEggs, milkshake));

        List<Recipe> results = recipeService.findRecipesByIngredients(List.of("eggs"));

        assertEquals(1, results.size());
        assertEquals(scrambledEggs.getName(), results.getFirst().getName());
    }

    @Test
    void testFindRecipesByIngredients_returnsEmptyOnNoIngredientRecipes() {
        Recipe scrambledEggs = new Recipe();

        when(recipeRepository.findAll()).thenReturn(List.of(scrambledEggs));

        List<Recipe> results = recipeService.findRecipesByIngredients(List.of("eggs"));

        assertTrue(results.isEmpty());
    }

    @Test
    void testFindRecipesByIngredients_returnsEmptyOnNullIngredientName() {
        Ingredient eggs = new Ingredient();

        Recipe scrambledEggs = new Recipe();
        scrambledEggs.setName("Scrambled Eggs");
        scrambledEggs.setIngredients(List.of(eggs));

        when(recipeRepository.findAll()).thenReturn(List.of(scrambledEggs));

        List<Recipe> results = recipeService.findRecipesByIngredients(List.of("eggs"));

        assertTrue(results.isEmpty());
    }

    @Test
    void testFindRecipesByIngredients_returnsEmptyWhenNoMatch() {
        Ingredient eggs = new Ingredient();
        eggs.setName("eggs");

        Recipe scrambledEggs = new Recipe();
        scrambledEggs.setName("Scrambled Eggs");
        scrambledEggs.setIngredients(List.of(eggs));

        when(recipeRepository.findAll()).thenReturn(List.of(scrambledEggs));

        List<Recipe> results = recipeService.findRecipesByIngredients(List.of("chicken"));

        assertTrue(results.isEmpty());
    }

    @Test
    void testFindRecipesByIngredients_returnsRecipeWhenInputIsSuperset() {
        Ingredient eggs = new Ingredient();
        eggs.setName("eggs");

        Ingredient bacon = new Ingredient();
        bacon.setName("bacon");

        Recipe eggsAndBacon = new Recipe();
        eggsAndBacon.setName("Eggs and bacon");
        eggsAndBacon.setIngredients(List.of(eggs, bacon));

        when(recipeRepository.findAll()).thenReturn(List.of(eggsAndBacon));

        List<Recipe> results = recipeService.findRecipesByIngredients(List.of("eggs", "bacon", "chicken"));

        assertEquals(1, results.size());
        assertEquals(eggsAndBacon.getName(), results.getFirst().getName());
    }

    @Test
    void testFindRecipesByIngredients_returnsEmptyWhenInputIsNotSuperset() {
        Ingredient milk = new Ingredient();
        milk.setName("milk");

        Ingredient banana = new Ingredient();
        banana.setName("banana");

        Recipe bananaSmoothie = new Recipe();
        bananaSmoothie.setName("Banana smoothie");
        bananaSmoothie.setIngredients(List.of(milk, banana));

        when(recipeRepository.findAll()).thenReturn(List.of(bananaSmoothie));

        List<Recipe> results = recipeService.findRecipesByIngredients(List.of("milk"));

        assertTrue(results.isEmpty());
    }

    @Test
    void testFindRecipesByIngredients_caseInsensitiveSearch() {
        Ingredient eggs = new Ingredient();
        eggs.setName("eggs");

        Recipe scrambledEggs = new Recipe();
        scrambledEggs.setName("Scrambled Eggs");
        scrambledEggs.setIngredients(List.of(eggs));

        when(recipeRepository.findAll()).thenReturn(List.of(scrambledEggs));

        List<Recipe> results = recipeService.findRecipesByIngredients(List.of("Eggs"));

        assertEquals(1, results.size());
        assertEquals(scrambledEggs.getName(), results.getFirst().getName());
    }

    @Test
    void testFindRecipesByIngredients_whitespaceInInput() {
        Ingredient eggs = new Ingredient();
        eggs.setName("eggs");

        Recipe scrambledEggs = new Recipe();
        scrambledEggs.setName("Scrambled Eggs");
        scrambledEggs.setIngredients(List.of(eggs));

        when(recipeRepository.findAll()).thenReturn(List.of(scrambledEggs));

        List<Recipe> results = recipeService.findRecipesByIngredients(List.of("   eggs   "));

        assertEquals(1, results.size());
        assertEquals(scrambledEggs.getName(), results.getFirst().getName());
    }

    @Test
    void testFindRecipesByIngredients_returnsEmptyWhenEmptyInput() {
        Ingredient eggs = new Ingredient();
        eggs.setName("eggs");

        Recipe scrambledEggs = new Recipe();
        scrambledEggs.setName("Scrambled Eggs");
        scrambledEggs.setIngredients(List.of(eggs));

        when(recipeRepository.findAll()).thenReturn(List.of(scrambledEggs));

        List<Recipe> results = recipeService.findRecipesByIngredients(List.of(""));

        assertTrue(results.isEmpty());
    }

    @Test
    void testFindRecipesByIngredients_returnsEmptyWhenNullInput() {
        List<Recipe> results = recipeService.findRecipesByIngredients(List.of(null));
        assertTrue(results.isEmpty());
    }

    @Test
    void testFindRecipesByIngredients_returnsEmptyWhenListIsNull() {
        List<Recipe> results = recipeService.findRecipesByIngredients(null);
        assertTrue(results.isEmpty());
    }
}
