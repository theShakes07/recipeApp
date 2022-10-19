package com.recipeapp.services;

import com.recipeapp.models.dtos.login.LoginRequest;
import com.recipeapp.models.entities.MyUser;
import com.recipeapp.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

  private static int workload = 12;

  private UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void saveUser(MyUser user) {
    String passwordHash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(workload));
    user.setPassword(passwordHash);
    userRepository.save(user);
  }

  @Override
  public MyUser login(LoginRequest loginRequest) {
    MyUser user = loadUserByUsername(loginRequest.getUsername());
    if(user == null || !checkPassword(loginRequest.getPassword(), user.getPassword())) {
      throw new RuntimeException("Bad password");
    } else {
      System.out.println("Password OK!");
      return user;
    }
  }

  @Override
  public void modifyUser(MyUser user) {

  }

  @Override
  public void deleteUser(MyUser user) {

  }

  @Override
  public MyUser loadUserByUsername(String username) throws UsernameNotFoundException {
    MyUser user = this.userRepository.findByUsername(username);
    if(user==null) {
      System.out.println("User not found.");
      throw new UsernameNotFoundException("User not found!");
    }
    return user;
  }

  private boolean checkPassword(String password, String storedPassword) {
    boolean pwd_verified = false;
    if(null == storedPassword|| !storedPassword.startsWith("$2a$"))
      throw new IllegalArgumentException("Invalid hash provided for comparison");
    pwd_verified = BCrypt.checkpw(password, storedPassword);
    return (pwd_verified);
  }

}
