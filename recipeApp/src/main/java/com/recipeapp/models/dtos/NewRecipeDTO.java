package com.recipeapp.models.dtos;

public class NewRecipeDTO {

  private String name;
  private String ingredient;
  private String directions;

  public NewRecipeDTO() {
  }

  public NewRecipeDTO(String name, String ingredient, String directions) {
    this.name = name;
    this.ingredient = ingredient;
    this.directions = directions;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIngredient() {
    return ingredient;
  }

  public void setIngredient(String ingredient) {
    this.ingredient = ingredient;
  }

  public String getDirections() {
    return directions;
  }

  public void setDirections(String directions) {
    this.directions = directions;
  }
}
