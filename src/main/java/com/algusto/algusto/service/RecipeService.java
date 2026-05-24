package com.algusto.algusto.service;

import com.algusto.algusto.dto.RecipeDTO;
import com.algusto.algusto.entity.Ingredient;
import com.algusto.algusto.entity.Recipe;
import com.algusto.algusto.mapper.RecipeMapper;
import com.algusto.algusto.repository.IngredientRepository;
import com.algusto.algusto.repository.RecipeRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    public RecipeService(
        RecipeRepository recipeRepository,
        IngredientRepository ingredientRepository
    ) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public List<RecipeDTO> getAllRecipes() {
        return recipeRepository
            .findAll()
            .stream()
            .map(RecipeMapper::toDTO)
            .toList();
    }

    public RecipeDTO saveRecipe(RecipeDTO recipeDTO) {
        List<Ingredient> ingredients = recipeDTO
            .ingredientNames()
            .stream()
            .map(name -> {
                Optional<Ingredient> found = ingredientRepository.findByName(
                    name
                );
                return found.orElseThrow(() ->
                    new RuntimeException("Ingredient not found: " + name)
                );
            })
            .toList();

        Recipe recipe = RecipeMapper.toEntity(recipeDTO, ingredients);
        return RecipeMapper.toDTO(recipeRepository.save(recipe));
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
