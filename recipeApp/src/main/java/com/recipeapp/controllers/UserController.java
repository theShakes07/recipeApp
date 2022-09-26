package com.recipeapp.controllers;

import com.recipeapp.models.entities.User;
import com.recipeapp.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/api/user/login")
  public String renderLoginPage() {
    return "login";
  }

  @GetMapping("/api/user/register")
  public String renderRegisterUser() {
    return "register_user";
  }

  @PostMapping("/api/user/reg")
  public String registerUser(@ModelAttribute("user") User user) {
    userService.addUser(user);
    return "redirect:/api/recipes";
  }

}
