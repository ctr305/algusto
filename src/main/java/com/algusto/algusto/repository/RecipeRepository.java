package com.algusto.algusto.repository;

import com.algusto.algusto.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query("""
    SELECT DISTINCT r FROM Recipe r
    JOIN r.ingredients i
    WHERE LOWER(i.name) IN :names
    GROUP BY r
    HAVING COUNT(DISTINCT i) = (
        SELECT COUNT(i2) FROM Recipe r2
        JOIN r2.ingredients i2
        WHERE r2 = r
        )
    """)
    List<Recipe> findByIngredientNames(@Param("names") List<String> names);
}

