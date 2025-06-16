package com.example.NutritionalGuide.Controllers;

import com.example.NutritionalGuide.Models.Recipie;
import com.example.NutritionalGuide.Repo.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired
    private RecipeRepo repo;

    @PostMapping
    public ResponseEntity<Recipie> createRecipe(@RequestBody Recipie recipe) {
        Recipie saved = repo.save(recipe);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipie> getRecipeById(@PathVariable String id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipie> updateRecipe(@PathVariable String id, @RequestBody Recipie updatedRecipe) {
        return repo.findById(id).map(existingRecipe -> {
            updatedRecipe.setId(id);
            Recipie saved = repo.save(updatedRecipe);
            return ResponseEntity.ok(saved);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable String id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Recipie>> getAllRecipes() {
        List<Recipie> recipes = repo.findAll();
        return ResponseEntity.ok(recipes);
    }
}
