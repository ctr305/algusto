package com.algusto.algusto.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Recipe {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;
   private String difficulty;
   private Integer prepTime;
   private String dishType;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "recipe_ingredients",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;

   @Column(length = 5000)
   private String instructions;

   public Long getId() {
       return id;
   }

   public void setId(Long id) {
       this.id = id;
   }

   public String getName() {
       return name;
   }

   public void setName(String name) {
       this.name = name;
   }

   public String getDifficulty() {
       return difficulty;
   }

   public void setDifficulty(String difficulty) {
       this.difficulty = difficulty;
   }

   public Integer getPrepTime() {
       return prepTime;
   }

   public void setPrepTime(Integer prepTime) {
       this.prepTime = prepTime;
   }

   public String getDishType() {
       return dishType;
   }

   public void setDishType(String dishType) {
       this.dishType = dishType;
   }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
       return instructions;
   }

   public void setInstructions(String instructions) {
       this.instructions = instructions;
   }
}
