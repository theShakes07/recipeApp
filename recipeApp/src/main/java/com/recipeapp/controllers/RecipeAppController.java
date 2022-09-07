package com.recipeapp.controllers;

import com.recipeapp.models.Recipe;
import com.recipeapp.models.dtos.NewRecipeDTO;
import com.recipeapp.services.RecipeAppService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeAppController {

  private RecipeAppService recipeAppService;

  public RecipeAppController(RecipeAppService recipeAppService) {
    this.recipeAppService = recipeAppService;
  }

  @GetMapping("/api/recipes")
  public ResponseEntity<List<Recipe>> getAllRecipe() {
    List<Recipe> recipes = recipeAppService.findAllRecipe();
    return ResponseEntity.status(200).body(recipes);
  }

  @PostMapping("/api/recipes")
  public ResponseEntity<Recipe> addNewRecipe(@RequestBody NewRecipeDTO recipeDTO) {
    Recipe recipe = recipeAppService.addNewRecipe(recipeDTO);
    return ResponseEntity.status(201).body(recipe);
  }

}
