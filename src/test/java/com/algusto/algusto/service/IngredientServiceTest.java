package com.algusto.algusto.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.algusto.algusto.dto.IngredientDTO;
import com.algusto.algusto.entity.Ingredient;
import com.algusto.algusto.repository.IngredientRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IngredientServiceTest {

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private IngredientService ingredientService;

    @Test
    void testGetAllIngredients() {
        Ingredient eggs = new Ingredient();
        eggs.setName("eggs");

        Ingredient milk = new Ingredient();
        milk.setName("milk");

        when(ingredientRepository.findAll()).thenReturn(List.of(eggs, milk));

        List<IngredientDTO> results = ingredientService.getAllIngredients();

        assertEquals(2, results.size());
        assertEquals(eggs.getName(), results.get(0).name());
        assertEquals(milk.getName(), results.get(1).name());
    }

    @Test
    void testGetAllIngredients_returnsEmptyWhenNoIngredients() {
        when(ingredientRepository.findAll()).thenReturn(List.of());

        List<IngredientDTO> results = ingredientService.getAllIngredients();

        assertTrue(results.isEmpty());
    }
}
