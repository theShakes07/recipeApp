package com.recipeapp.services;

import com.recipeapp.models.entities.User;
import com.recipeapp.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void addUser(User user) {
    userRepository.save(user);
  }

  @Override
  public void modifyUser(User user) {

  }

  @Override
  public void deleteUser(User user) {

  }
}
