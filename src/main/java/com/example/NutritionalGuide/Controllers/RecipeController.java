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
    public Recipie createRecipe(@RequestBody Recipie recipe) {
        return repo.save(recipe);
    }

//    @GetMapping
//    public List<Recipie> getAllRecipes(
//            @RequestParam(required = false) String category,
//            @RequestParam(required = false) List<String> ingredients,
//            @RequestParam(required = false) Integer maxCalories
//    ) {
//        if (category != null) return repo.findByCategory(category);
//        if (ingredients != null) return repo.findByIngredientsIn(ingredients);
//        if (maxCalories != null) return repo.findByCaloriesLessThanEqual(maxCalories);
//        return repo.findAll();
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipie> getRecipeById(@PathVariable String id) {
        return repo.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipie> update(@PathVariable String id, @RequestBody Recipie r) {
        return repo.findById(id).map(recipe -> {
            r.setId(id);
            return ResponseEntity.ok(repo.save(r));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repo.deleteById(id);
    }
    @PostMapping("/full")
    public ResponseEntity<Recipie> createFullRecipe(@RequestBody Recipie recipe) {
        Recipie saved = repo.save(recipe);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Recipie>> getAllRecipes() {
        List<Recipie> recipes = repo.findAll();
        return ResponseEntity.ok(recipes);
    }

}
