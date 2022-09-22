package com.recipeapp.controllers;

import com.recipeapp.models.Recipe;
import com.recipeapp.models.dtos.NewRecipeDTO;
import com.recipeapp.models.dtos.ReturnRecipeDTO;
import com.recipeapp.services.RecipeAppService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecipeAppController {

  private RecipeAppService recipeAppService;

  public RecipeAppController(RecipeAppService recipeAppService) {
    this.recipeAppService = recipeAppService;
  }

  @GetMapping("/search")
  public String searchRecipe(Model model,
                             @RequestParam(required = false, name = "search") String search) {
    model.addAttribute("recipeList", recipeAppService.searcher(search));
    return "recipes";
  }

  @GetMapping("/api/recipes")
  public String renderPage(Model model) {
    List<Recipe> recipeList = recipeAppService.returnSortedAllRecipeList();
    model.addAttribute("recipeList", recipeList);
    return "recipes";
  }

  @GetMapping("/api/recipes/{id}")
  public String renderRecipeById(Model model,
                             @PathVariable(value = "id") int id) {
    Recipe recipe = recipeAppService.findRecipeById(id);
    model.addAttribute("recipe", recipe);
    ReturnRecipeDTO recipeDTO = recipeAppService.returnDTOConverter(recipe);
    model.addAttribute("recipeIngredients", recipeDTO.getIngredients());
    model.addAttribute("recipeDirections", recipeDTO.getDirections());
    return "recipe";
  }

  @GetMapping("/api/randomRecipe")
  public String returnRandomRecipe() {
    Recipe recipe = recipeAppService.randomRecipe();
    int randomRecipeNumber = recipe.getId();
    return "redirect:/api/recipes/" + randomRecipeNumber;
  }

  @GetMapping("/api/addRecipe")
  public String renderAddRecipePage(Model model) {

    return "register_recipe";
  }

  @PostMapping("/api/registerRecipe")
  public String addNewRecipe(@ModelAttribute("newrecipedto") NewRecipeDTO recipeDTO) {
    recipeAppService.addNewRecipe(recipeDTO);
    return "redirect:/api/recipes";
  }


}
