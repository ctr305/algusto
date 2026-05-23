package com.algusto.algusto.service;

import com.algusto.algusto.dto.RecipeDTO;
import com.algusto.algusto.mapper.RecipeMapper;
import com.algusto.algusto.repository.RecipeRepository;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<RecipeDTO> getAllRecipes() {
        return recipeRepository
            .findAll()
            .stream()
            .map(RecipeMapper::toDTO)
            .toList();
    }

    public RecipeDTO saveRecipe(RecipeDTO recipe) {
        return RecipeMapper.toDTO(
            recipeRepository.save(RecipeMapper.toEntity(recipe))
        );
    }

    public List<RecipeDTO> findRecipesByIngredients(
        List<String> ingredientNames
    ) {
        if (
            ingredientNames == null || ingredientNames.isEmpty()
        ) return List.of();

        List<String> normalizedNames = ingredientNames
            .stream()
            .filter(Objects::nonNull)
            .map(name -> name.trim().toLowerCase())
            .filter(name -> !name.isEmpty())
            .toList();

        if (normalizedNames.isEmpty()) return List.of();

        return recipeRepository
            .findByIngredientNames(normalizedNames)
            .stream()
            .map(RecipeMapper::toDTO)
            .toList();
    }
}
