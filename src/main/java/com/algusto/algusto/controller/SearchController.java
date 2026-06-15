package com.algusto.algusto.controller;

import com.algusto.algusto.service.RecipeService;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    private final RecipeService recipeService;

    public SearchController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam String ingredients, Model model) {
        List<String> parsed = Arrays.stream(ingredients.split(","))
            .map(String::trim)
            .filter(s -> !s.isEmpty())
            .toList();
        model.addAttribute(
            "recipes",
            recipeService.findRecipesByIngredients(parsed)
        );
        return "fragments/results :: results";
    }
}
