package com.algusto.algusto.mapper;

import com.algusto.algusto.dto.RecipeDTO;
import com.algusto.algusto.entity.Ingredient;
import com.algusto.algusto.entity.Recipe;
import java.util.List;

public class RecipeMapper {

    public static RecipeDTO toDTO(Recipe recipe) {
        return new RecipeDTO(
            recipe.getId(),
            recipe.getName(),
            recipe.getDifficulty(),
            recipe.getPrepTime(),
            recipe.getDishType(),
            recipe.getInstructions(),
            recipe.getIngredients().stream().map(Ingredient::getName).toList()
        );
    }

    public static Recipe toEntity(
        RecipeDTO recipeDTO,
        List<Ingredient> ingredients
    ) {
        Recipe recipe = new Recipe();
        recipe.setName(recipeDTO.name());
        recipe.setDifficulty(recipeDTO.difficulty());
        recipe.setPrepTime(recipeDTO.prepTime());
        recipe.setDishType(recipeDTO.dishType());
        recipe.setInstructions(recipeDTO.instructions());
        recipe.setIngredients(ingredients);
        return recipe;
    }
}
