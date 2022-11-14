package com.recipeapp.controllers;

import com.recipeapp.models.entities.MyUser;
import com.recipeapp.models.entities.Recipe;
import com.recipeapp.models.dtos.NewRecipeDTO;
import com.recipeapp.models.dtos.ReturnRecipeDTO;
import com.recipeapp.security.JwtUtil;
import com.recipeapp.services.RecipeService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
  private JwtUtil jwtUtil;

  public RecipeAppController(RecipeService recipeService, JwtUtil jwtUtil) {
    this.recipeService = recipeService;
    this.jwtUtil = jwtUtil;
  }

  @GetMapping("/")
  public String renderIndexPage(@CookieValue(value = "Bearer", required = false) String token,
                                Model model) {
    isLoggedIn = loggedInChecker(token);
    model.addAttribute("isLoggedIn", isLoggedIn);
    return "index";
  }

  @GetMapping("api/recipes/search")
  public String searchRecipe(@CookieValue(value = "Bearer", required = false) String token,
                             Model model,
                             @RequestParam(required = false, name = "search") String search) {
    isLoggedIn = loggedInChecker(token);
    model.addAttribute("isLoggedIn", isLoggedIn);
    model.addAttribute("recipeList", recipeService.searcher(search));
    return "recipes";
  }

  @GetMapping("/api/recipes")
  public String renderPage(@CookieValue(value = "Bearer", required = false) String token,
                           Model model) {
    List<Recipe> recipeList = recipeService.returnSortedAllRecipeList();
    isLoggedIn = loggedInChecker(token);
    model.addAttribute("isLoggedIn", isLoggedIn);
    model.addAttribute("recipeList", recipeList);
    return "recipes";
  }

  @GetMapping("/api/recipes/{id}")
  public String renderRecipeById(@CookieValue(value = "Bearer", required = false) String token,
                                 Model model,
                             @PathVariable(value = "id") int id) {
    Recipe recipe = recipeService.findRecipeById(id);
    model.addAttribute("recipe", recipe);
    ReturnRecipeDTO recipeDTO = recipeService.returnDTOConverter(recipe);
    model.addAttribute("recipeIngredients", recipeDTO.getIngredients());
    model.addAttribute("recipeDirections", recipeDTO.getDirections());
    model.addAttribute("author", recipe.getOwnerUser().getUsername());
    isLoggedIn = loggedInChecker(token);
    model.addAttribute("isLoggedIn", isLoggedIn);
    return "recipe";
  }

  @GetMapping("/api/randomRecipe")
  public String returnRandomRecipe() {
    Recipe recipe = recipeService.randomRecipe();
    int randomRecipeId= recipe.getId();
    return "redirect:/api/recipes/" + randomRecipeId;
  }

  @GetMapping("/api/addRecipe")
  public String renderAddRecipePage(@CookieValue(value = "Bearer", required = false) String token,
                                    Model model) {
    isLoggedIn = loggedInChecker(token);
    model.addAttribute("isLoggedIn", isLoggedIn);
    model.addAttribute("newrecipedto", new NewRecipeDTO());
    return "register_recipe";
  }

  @PostMapping("/api/addRecipe")
  public String addNewRecipe(@Valid @ModelAttribute("newrecipedto") NewRecipeDTO recipeDTO,
                             BindingResult bindingResult,
                             Model model,
                             @CookieValue(value = "Bearer", required = false) String token) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("isLoggedIn", isLoggedIn);
      return "register_recipe";
    }
    String username = jwtUtil.extractUsername(token);
    recipeService.addNewRecipe(recipeDTO, username);
    return "redirect:/api/recipes";
  }

  private boolean loggedInChecker(String token) {
    return isLoggedIn = token != null;
  }

}
