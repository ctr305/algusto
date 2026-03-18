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
    void testFindRecipesByIngredients_caseInsensitiveSearch() {
        Ingredient eggs = new Ingredient();
        eggs.setName("eggs");

        Recipe scrambledEggs = new Recipe();
        scrambledEggs.setName("Scrambled Eggs");
        scrambledEggs.setIngredients(List.of(eggs));

        when(recipeRepository.findByIngredientNames(any())).thenReturn(List.of(scrambledEggs));

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

        when(recipeRepository.findByIngredientNames(any())).thenReturn(List.of(scrambledEggs));

        List<Recipe> results = recipeService.findRecipesByIngredients(List.of("   eggs   "));

        assertEquals(1, results.size());
        assertEquals(scrambledEggs.getName(), results.getFirst().getName());
    }

    @Test
    void testFindRecipesByIngredients_returnsEmptyWhenEmptyInput() {
        List<Recipe> results = recipeService.findRecipesByIngredients(List.of(""));
        assertTrue(results.isEmpty());
    }

    @Test
    void testFindRecipesByIngredients_returnsEmptyWhenListIsNull() {
        List<Recipe> results = recipeService.findRecipesByIngredients(null);
        assertTrue(results.isEmpty());
    }
}
