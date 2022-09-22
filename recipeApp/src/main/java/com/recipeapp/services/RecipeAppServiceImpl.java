package com.recipeapp.services;

import com.recipeapp.models.Recipe;
import com.recipeapp.models.dtos.NewRecipeDTO;
import com.recipeapp.models.dtos.ReturnRecipeDTO;
import com.recipeapp.repositories.RecipeRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;
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
  public Recipe findRecipeById(int id) {
    return recipeRepository.findById(id);
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

  @Override
  public List<Recipe> searcher(String text) {
    if(text == null || text.trim().isEmpty()) {
      return findAllRecipe();
    }
    else {
      return findAllRecipe().stream()
          .filter(recipe -> recipe.getRecipeName().toLowerCase().contains(text.toLowerCase()))
          .collect(Collectors.toList());
    }
  }

  @Override
  public Recipe randomRecipe() {
    List<Integer> recipeIdList = getAllRecipeId(findAllRecipe());
    Random random = new Random();
    int randomRecipeId = recipeIdList.get(random.nextInt(recipeIdList.size()));
    return findRecipeById(randomRecipeId);
  }

  private Recipe recipeConverter(NewRecipeDTO recipeDTO) {
    Recipe recipe = new Recipe();
    recipe.setRecipeName(recipeDTO.getName());
    recipe.setIngredient(recipeDTO.getIngredient());
    recipe.setDirections(recipeDTO.getDirections());
    return recipe;
  }

  private List<Integer> getAllRecipeId(List<Recipe> recipeList) {
    List<Integer> recipeIdList = new ArrayList<>();
    for(Recipe r : recipeList) {
      recipeIdList.add(r.getId());
    }
    return recipeIdList;
  }

  @Override
  public ReturnRecipeDTO returnDTOConverter(Recipe recipe) {
    ReturnRecipeDTO recipeDTO = new ReturnRecipeDTO();
    recipeDTO.setName(recipe.getRecipeName());
    recipeDTO.setIngredients(Arrays.asList(recipe.getIngredient().trim().split("/")));
    recipeDTO.setDirections(Arrays.asList(recipe.getDirections().trim().split("/")));
    return recipeDTO;
  }

}
