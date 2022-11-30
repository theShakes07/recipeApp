package com.recipeapp.services;

import com.recipeapp.models.entities.Recipe;
import com.recipeapp.models.dtos.NewRecipeDTO;
import com.recipeapp.models.dtos.ReturnRecipeDTO;
import java.util.List;

public interface RecipeService {

  List<Recipe> findAllRecipe();

  List<Recipe> returnSortedAllRecipeList();

  List<Recipe> returnFavouriteRecipes(String username);

  Recipe addNewRecipe(NewRecipeDTO recipeDTO, String username);

  void modifyRecipe(NewRecipeDTO recipeDTO, Integer recipeId);

  void deleteRecipe(Integer recipeId);

  void addRecipeToFavourites(String username, int recipeId);

  Recipe findRecipeById(int id);

  List<Recipe> searcher(String text);

  Recipe randomRecipe();

  ReturnRecipeDTO returnDTOConverter(Recipe recipe);

  boolean favRecipeChecker(String username, int recipeId);

}