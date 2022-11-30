package com.recipeapp.controllers;

import com.recipeapp.security.JwtUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DisplayController extends ResponseEntityExceptionHandler {

  private JwtUtil jwtUtil;

  public DisplayController(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @ModelAttribute("loggedinuser")
  public String getLoggedUserName(@CookieValue(value = "Bearer", required = false) String token,
                                  Model model) {
    if (token == null) {
      return "navbar";
    }
    String username = jwtUtil.extractUsername(token);
    model.addAttribute("loggedinuser", username);
    return username;
  }

}