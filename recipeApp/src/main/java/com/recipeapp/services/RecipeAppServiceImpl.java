package com.recipeapp.services;

import com.recipeapp.models.Recipe;
import com.recipeapp.models.dtos.NewRecipeDTO;
import com.recipeapp.repositories.RecipeRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RecipeAppServiceImpl implements RecipeAppService {

  private RecipeRepository recipeRepository;

  public RecipeAppServiceImpl(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  @Override
  public List<Recipe> findAllRecipe() {
    return recipeRepository.findAll();
  }

  @Override
  public Recipe addNewRecipe(NewRecipeDTO recipeDTO) {
    Recipe recipe = recipeConverter(recipeDTO);
    recipeRepository.save(recipe);
    return recipe;
  }

  @Override
  public void modifyRecipe(Recipe recipe, Integer recipeId) {

  }

  @Override
  public void deleteRecipe(Integer recipeId) {

  }

  private Recipe recipeConverter(NewRecipeDTO recipeDTO) {
    Recipe recipe = new Recipe();
    recipe.setName(recipeDTO.getName());
    recipe.setIngredient(recipeDTO.getIngredient());
    recipe.setDirections(recipeDTO.getDirections());
    return recipe;
  }
}
