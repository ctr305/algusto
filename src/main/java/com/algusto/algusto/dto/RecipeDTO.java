package com.algusto.algusto.dto;

import java.util.List;

public record RecipeDTO(
    Long id,
    String name,
    String difficulty,
    Integer prepTime,
    String dishType,
    String instructions,
    List<String> ingredientNames
) {}
