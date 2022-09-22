package com.recipeapp.repositories;

import com.recipeapp.models.Recipe;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

  List<Recipe> findAll();

  Recipe findById(int id);
}
