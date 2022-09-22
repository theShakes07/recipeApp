package com.recipeapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "recipes")
public class Recipe {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String recipeName;
  private String ingredient;
  private String directions;

  public Recipe() {
  }

  public Recipe(String recipeName, String ingredient, String directions) {
    this.recipeName = recipeName;
    this.ingredient = ingredient;
    this.directions = directions;
  }

  public Recipe(Integer id, String recipeName, String ingredient, String directions) {
    this.id = id;
    this.recipeName = recipeName;
    this.ingredient = ingredient;
    this.directions = directions;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getRecipeName() {
    return recipeName;
  }

  public void setRecipeName(String recipeName) {
    this.recipeName = recipeName;
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
