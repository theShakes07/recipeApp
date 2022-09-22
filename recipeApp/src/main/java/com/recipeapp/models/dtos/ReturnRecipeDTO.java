package com.recipeapp.models.dtos;

import java.util.List;

public class ReturnRecipeDTO {

  private String name;
  private List<String> ingredients;
  private List<String> directions;

  public ReturnRecipeDTO() {
  }

  public ReturnRecipeDTO(String name, List<String> ingredients,
                         List<String> directions) {
    this.name = name;
    this.ingredients = ingredients;
    this.directions = directions;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<String> ingredients) {
    this.ingredients = ingredients;
  }

  public List<String> getDirections() {
    return directions;
  }

  public void setDirections(List<String> directions) {
    this.directions = directions;
  }
}
