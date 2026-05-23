package com.algusto.algusto.service;

import com.algusto.algusto.dto.IngredientDTO;
import com.algusto.algusto.mapper.IngredientMapper;
import com.algusto.algusto.repository.IngredientRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<IngredientDTO> getAllIngredients() {
        return ingredientRepository
            .findAll()
            .stream()
            .map(ingredient -> IngredientMapper.toDTO(ingredient))
            .toList();
    }
}
