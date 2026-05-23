package com.algusto.algusto.mapper;

import com.algusto.algusto.dto.IngredientDTO;
import com.algusto.algusto.entity.Ingredient;

public class IngredientMapper {

    public static IngredientDTO toDTO(Ingredient ingredient) {
        return new IngredientDTO(
            ingredient.getId(),
            ingredient.getName(),
            ingredient.getMeasurementUnit()
        );
    }

    public static Ingredient toEntity(IngredientDTO ingredientDTO) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientDTO.id());
        ingredient.setName(ingredientDTO.name());
        ingredient.setMeasurementUnit(ingredientDTO.measurementUnit());
        return ingredient;
    }
}
