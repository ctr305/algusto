package com.algusto.algusto.controller;

import com.algusto.algusto.dto.IngredientDTO;
import com.algusto.algusto.service.IngredientService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ingredients")
@CrossOrigin
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public List<IngredientDTO> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    /* TODO ingredient creation logic
    @PostMapping
    public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.saveIngredient(ingredient);
    }
     */
}
