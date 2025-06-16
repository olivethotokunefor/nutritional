package com.example.NutritionalGuide.Repo;

import com.example.NutritionalGuide.Models.Recipie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RecipeRepo extends MongoRepository<Recipie, String> {
    List<Recipie> findByCategory(String category);
    List<Recipie> findByIngredientsIn(List<String> ingredients);
    List<Recipie> findByCaloriesLessThanEqual(int maxCalories);


}
