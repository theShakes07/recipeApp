package com.recipeapp.services;

import com.recipeapp.models.Recipe;
import com.recipeapp.models.dtos.NewRecipeDTO;
import java.util.List;

public interface RecipeAppService {

  List<Recipe> findAllRecipe();

  Recipe addNewRecipe(NewRecipeDTO recipeDTO);

  void modifyRecipe(Recipe recipe, Integer recipeId);

  void deleteRecipe(Integer recipeId);

}
