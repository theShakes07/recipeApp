package com.recipeapp.services;

import com.recipeapp.models.dtos.login.LoginRequest;
import com.recipeapp.models.entities.MyUser;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

  void saveUser(MyUser user);

  MyUser login(LoginRequest loginRequest);

  void modifyUser(MyUser user);

  void deleteUser(MyUser user);

  UserDetails loadUserByUsername(String username);

  Cookie cookieGenerator(String jwt);

  Cookie logout(HttpServletRequest request);

  }
