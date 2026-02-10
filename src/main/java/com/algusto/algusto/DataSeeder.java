package com.algusto.algusto;

import com.algusto.algusto.entity.Ingredient;
import com.algusto.algusto.entity.Recipe;
import com.algusto.algusto.repository.IngredientRepository;
import com.algusto.algusto.repository.RecipeRepository;
import org.springframework.boot.CommandLineRunner;
import java.util.Arrays;

public class DataSeeder implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    public DataSeeder(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (recipeRepository.count() > 0) return; // Only seed if empty

        // Create Ingredients

        Ingredient eggs = new Ingredient();
        eggs.setName("eggs");
        eggs.setMeasurementUnit("unit");
        ingredientRepository.save(eggs);

        Ingredient butter = new Ingredient();
        butter.setName("butter");
        butter.setMeasurementUnit("grams");
        ingredientRepository.save(butter);

        Ingredient salt = new Ingredient();
        salt.setName("salt");
        salt.setMeasurementUnit("grams");
        ingredientRepository.save(salt);

        Ingredient flour = new Ingredient();
        flour.setName("flour");
        flour.setMeasurementUnit("grams");
        ingredientRepository.save(flour);

        Ingredient sugar = new Ingredient();
        sugar.setName("sugar");
        sugar.setMeasurementUnit("grams");
        ingredientRepository.save(sugar);

        Ingredient milk = new Ingredient();
        milk.setName("milk");
        milk.setMeasurementUnit("ml");
        ingredientRepository.save(milk);

        Ingredient cocoaPowder = new Ingredient();
        cocoaPowder.setName("cocoa powder");
        cocoaPowder.setMeasurementUnit("grams");
        ingredientRepository.save(cocoaPowder);

        Ingredient chickenBreast = new Ingredient();
        chickenBreast.setName("chicken breast");
        chickenBreast.setMeasurementUnit("grams");
        ingredientRepository.save(chickenBreast);

        Ingredient lettuce = new Ingredient();
        lettuce.setName("lettuce");
        lettuce.setMeasurementUnit("grams");
        ingredientRepository.save(lettuce);

        Ingredient tomato = new Ingredient();
        tomato.setName("tomato");
        tomato.setMeasurementUnit("unit");
        ingredientRepository.save(tomato);

        Ingredient oliveOil = new Ingredient();
        oliveOil.setName("olive oil");
        oliveOil.setMeasurementUnit("ml");
        ingredientRepository.save(oliveOil);

        // Create Recipes

        // 1. Scrambled Eggs
        Recipe scrambledEggs = new Recipe();
        scrambledEggs.setName("Scrambled Eggs");
        scrambledEggs.setDifficulty("easy");
        scrambledEggs.setPrepTime(10);
        scrambledEggs.setDishType("main");
        scrambledEggs.setInstructions(
                "Beat eggs, melt butter in pan, cook eggs gently while stirring, season with salt."
        );
        scrambledEggs.setIngredients(Arrays.asList(eggs, butter, salt));
        recipeRepository.save(scrambledEggs);


        // 2. Pancakes
        Recipe pancakes = new Recipe();
        pancakes.setName("Pancakes");
        pancakes.setDifficulty("easy");
        pancakes.setPrepTime(20);
        pancakes.setDishType("breakfast");
        pancakes.setInstructions(
                "Mix flour, milk, eggs, and sugar into a smooth batter. Pour into pan and cook until golden on both sides."
        );
        pancakes.setIngredients(Arrays.asList(flour, milk, eggs, sugar, butter));
        recipeRepository.save(pancakes);


        // 3. Grilled Chicken Salad
        Recipe grilledChickenSalad = new Recipe();
        grilledChickenSalad.setName("Grilled Chicken Salad");
        grilledChickenSalad.setDifficulty("medium");
        grilledChickenSalad.setPrepTime(30);
        grilledChickenSalad.setDishType("lunch");
        grilledChickenSalad.setInstructions(
                "Season and grill chicken. Chop lettuce and tomato. Slice chicken and toss with vegetables, olive oil, and salt."
        );
        grilledChickenSalad.setIngredients(Arrays.asList(chickenBreast, lettuce, tomato, oliveOil, salt));
        recipeRepository.save(grilledChickenSalad);


        // 4. Chocolate Cake
        Recipe chocolateCake = new Recipe();
        chocolateCake.setName("Chocolate Cake");
        chocolateCake.setDifficulty("hard");
        chocolateCake.setPrepTime(60);
        chocolateCake.setDishType("dessert");
        chocolateCake.setInstructions(
                "Mix flour, cocoa powder, and sugar. Add eggs and melted butter. Pour into baking pan and bake at 180°C for 35-40 minutes."
        );
        chocolateCake.setIngredients(Arrays.asList(flour, cocoaPowder, sugar, eggs, butter));
        recipeRepository.save(chocolateCake);
    }
}
