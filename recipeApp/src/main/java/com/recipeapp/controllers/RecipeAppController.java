package com.recipeapp.controllers;

import com.recipeapp.models.entities.Recipe;
import com.recipeapp.models.dtos.NewRecipeDTO;
import com.recipeapp.models.dtos.ReturnRecipeDTO;
import com.recipeapp.services.RecipeService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecipeAppController {

  boolean isLoggedIn = false;

  private RecipeService recipeService;

  public RecipeAppController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @GetMapping("/search")
  public String searchRecipe(Model model,
                             @RequestParam(required = false, name = "search") String search) {
    model.addAttribute("recipeList", recipeService.searcher(search));
    return "recipes";
  }

  @GetMapping("/api/recipes")
  public String renderPage(@CookieValue(value = "Bearer", required = false) String token,
                           Model model) {
    List<Recipe> recipeList = recipeService.returnSortedAllRecipeList();
    isLoggedIn = loggedInChecker(token);
    model.addAttribute("recipeList", recipeList);
    model.addAttribute("isLoggedIn", isLoggedIn);
    return "recipes";
  }

  @GetMapping("/api/recipes/{id}")
  public String renderRecipeById(Model model,
                             @PathVariable(value = "id") int id) {
    Recipe recipe = recipeService.findRecipeById(id);
    model.addAttribute("recipe", recipe);
    ReturnRecipeDTO recipeDTO = recipeService.returnDTOConverter(recipe);
    model.addAttribute("recipeIngredients", recipeDTO.getIngredients());
    model.addAttribute("recipeDirections", recipeDTO.getDirections());
    return "recipe";
  }

  @GetMapping("/api/randomRecipe")
  public String returnRandomRecipe() {
    Recipe recipe = recipeService.randomRecipe();
    int randomRecipeId= recipe.getId();
    return "redirect:/api/recipes/" + randomRecipeId;
  }

  @GetMapping("/api/addRecipe")
  public String renderAddRecipePage(Model model) {

    return "register_recipe";
  }

  @PostMapping("/api/registerRecipe")
  public String addNewRecipe(@ModelAttribute("newrecipedto") NewRecipeDTO recipeDTO) {
    recipeService.addNewRecipe(recipeDTO);
    return "redirect:/api/recipes";
  }

  private boolean loggedInChecker(String token) {
    return isLoggedIn = token != null;
  }

}
