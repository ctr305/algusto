package com.algusto.algusto.service;

import com.algusto.algusto.entity.Recipe;
import com.algusto.algusto.repository.RecipeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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

    public List<Recipe> findRecipesByIngredients(List<String> ingredientNames) {
        if (ingredientNames == null || ingredientNames.isEmpty()) return List.of();

        Set<String> normalizedNames = ingredientNames.stream()
                .filter(Objects::nonNull)
                .map(name -> name.trim().toLowerCase())
                .collect(Collectors.toSet());

        if (normalizedNames.isEmpty()) return List.of();

        return recipeRepository.findAll().stream()
                .filter(recipe -> recipe.getIngredients() != null &&
                        !recipe.getIngredients().isEmpty() &&
                        recipe.getIngredients().stream()
                                .allMatch(ingredient -> ingredient.getName() != null &&
                                        normalizedNames.contains(
                                                ingredient.getName().trim().toLowerCase())))
                .collect(Collectors.toList());
    }
}
