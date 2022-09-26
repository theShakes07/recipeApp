package com.recipeapp.services;

import com.recipeapp.models.entities.User;

public interface UserService {

  void addUser(User user);

  void modifyUser(User user);

  void deleteUser(User user);

}
