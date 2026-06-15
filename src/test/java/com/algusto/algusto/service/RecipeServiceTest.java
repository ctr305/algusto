package com.algusto.algusto.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.algusto.algusto.dto.RecipeDTO;
import com.algusto.algusto.entity.Ingredient;
import com.algusto.algusto.entity.Recipe;
import com.algusto.algusto.repository.RecipeRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

        when(recipeRepository.findByIngredientNames(any())).thenReturn(
            List.of(scrambledEggs)
        );

        List<RecipeDTO> results = recipeService.findRecipesByIngredients(
            List.of("Eggs")
        );

        assertEquals(1, results.size());
        assertEquals(scrambledEggs.getName(), results.getFirst().name());
    }

    @Test
    void testFindRecipesByIngredients_whitespaceInInput() {
        Ingredient eggs = new Ingredient();
        eggs.setName("eggs");

        Recipe scrambledEggs = new Recipe();
        scrambledEggs.setName("Scrambled Eggs");
        scrambledEggs.setIngredients(List.of(eggs));

        when(recipeRepository.findByIngredientNames(any())).thenReturn(
            List.of(scrambledEggs)
        );

        List<RecipeDTO> results = recipeService.findRecipesByIngredients(
            List.of("   eggs   ")
        );

        assertEquals(1, results.size());
        assertEquals(scrambledEggs.getName(), results.getFirst().name());
    }

    @Test
    void testFindRecipesByIngredients_normalizesWhitespaceBeforeQuerying() {
        when(recipeRepository.findByIngredientNames(any())).thenReturn(
            List.of()
        );

        recipeService.findRecipesByIngredients(List.of("   eggs   "));

        verify(recipeRepository).findByIngredientNames(List.of("eggs"));
    }

    @Test
    void testFindRecipesByIngredients_lowercasesInputBeforeQuerying() {
        when(recipeRepository.findByIngredientNames(any())).thenReturn(
            List.of()
        );

        recipeService.findRecipesByIngredients(List.of("Eggs"));

        verify(recipeRepository).findByIngredientNames(List.of("eggs"));
    }
}
