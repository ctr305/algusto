package com.algusto.algusto.controller;

import com.algusto.algusto.entity.Ingredient;
import com.algusto.algusto.service.IngredientService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/ingredients")
@CrossOrigin
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) { this.ingredientService = ingredientService; }

    @GetMapping
    public List<Ingredient> getAllIngredients() { return ingredientService.getAllIngredients(); }

    /* TODO ingredient creation logic
    @PostMapping
    public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.saveIngredient(ingredient);
    }
     */
}
