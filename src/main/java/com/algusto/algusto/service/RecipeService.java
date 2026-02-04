package com.algusto.algusto.service;

import com.algusto.algusto.entity.Recipe;
import com.algusto.algusto.repository.RecipeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }
}
