package com.recipeapp.repositories;

import com.recipeapp.models.entities.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

  List<User> findAll();

  User findById(int id);
}
