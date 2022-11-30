package com.recipeapp.services;

import com.recipeapp.models.dtos.ChangeDTO;
import com.recipeapp.models.entities.MyUser;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

  String saveUser(MyUser user);

  String modifyUser(String username, ChangeDTO changeDTO);

  void deleteUser(MyUser user);

  UserDetails loadUserByUsername(String username);

  Cookie cookieGenerator(String jwt);

  Cookie logout(HttpServletRequest request);

}