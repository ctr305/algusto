package com.algusto.algusto.controller;

import com.algusto.algusto.dto.RecipeDTO;
import com.algusto.algusto.service.RecipeService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes")
@CrossOrigin
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<RecipeDTO> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @PostMapping
    public RecipeDTO createRecipe(@RequestBody RecipeDTO recipe) {
        return recipeService.saveRecipe(recipe);
    }

    @PostMapping("/search")
    public List<RecipeDTO> searchByIngredients(
        @RequestBody List<String> ingredients
    ) {
        return recipeService.findRecipesByIngredients(ingredients);
    }
}
