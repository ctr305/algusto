package com.algusto.algusto.mapper;

import com.algusto.algusto.dto.RecipeDTO;
import com.algusto.algusto.entity.Recipe;

public class RecipeMapper {

    public static RecipeDTO toDTO(Recipe recipe) {
        return new RecipeDTO(
            recipe.getId(),
            recipe.getName(),
            recipe.getInstructions()
        );
    }

    public static Recipe toEntity(RecipeDTO recipeDTO) {
        Recipe recipe = new Recipe();
        recipe.setId(recipeDTO.id());
        recipe.setName(recipeDTO.name());
        recipe.setInstructions(recipeDTO.description());
        return recipe;
    }
}
