package com.algusto.algusto.service;

import com.algusto.algusto.entity.Ingredient;
import com.algusto.algusto.repository.IngredientRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getAllIngredients() { return ingredientRepository.findAll(); }
}
