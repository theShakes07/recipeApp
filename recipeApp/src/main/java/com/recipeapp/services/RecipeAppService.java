package com.recipeapp.services;

import com.recipeapp.models.Recipe;
import com.recipeapp.models.dtos.NewRecipeDTO;
import com.recipeapp.models.dtos.ReturnRecipeDTO;
import java.util.List;

public interface RecipeAppService {

  List<Recipe> findAllRecipe();

  List<Recipe> returnSortedAllRecipeList();

  Recipe addNewRecipe(NewRecipeDTO recipeDTO);

  void modifyRecipe(Recipe recipe, Integer recipeId);

  void deleteRecipe(Integer recipeId);

  Recipe findRecipeById(int id);

  List<Recipe> searcher(String text);

  Recipe randomRecipe();

  ReturnRecipeDTO returnDTOConverter(Recipe recipe);

}
