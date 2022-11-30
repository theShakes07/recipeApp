package com.recipeapp.controllers;

import com.recipeapp.exceptions.types.IncorrectPasswordException;
import com.recipeapp.models.dtos.ChangeDTO;
import com.recipeapp.models.dtos.login.LoginRequest;
import com.recipeapp.models.entities.MyUser;
import com.recipeapp.security.JwtUtil;
import com.recipeapp.services.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

  private AuthenticationManager authenticationManager;
  private JwtUtil jwtUtil;
  private UserService userService;

  public UserController(
      AuthenticationManager authenticationManager, JwtUtil jwtUtil,
      UserService userService) {
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;
    this.userService = userService;
  }

  @GetMapping("/api/user/register")
  public String renderRegisterUser(Model model) {
    model.addAttribute("user", new MyUser());
    return "register_user";
  }

  @PostMapping("/api/user/register")
  public String registerUser(@Valid @ModelAttribute("user") MyUser user,
                             BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      return "register_user";
    }
    String existedUsername = userService.saveUser(user);
    if (existedUsername != null) {
      model.addAttribute("existedUsername", existedUsername);
      return "register_user";
    }
    return "redirect:/api/recipes";
  }

  @GetMapping("/api/user/login")
  public String renderLoginPage() {
    return "login";
  }

  @PostMapping("/api/auth")
  public String createToken(@ModelAttribute("loginrequest") LoginRequest loginRequest,
                            HttpServletResponse resp,
                            Model model) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
              loginRequest.getPassword())
      );
    } catch (BadCredentialsException e) {
      model.addAttribute("ex", "Hibás felhasználónév vagy jelszó.");
      return "login";
    }
    final UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());
    final String jwt = jwtUtil.generateToken(userDetails);
    resp.addCookie(userService.cookieGenerator(jwt));
    return "redirect:/api/recipes";
  }

  @GetMapping("/api/user/logout")
  public String logout(HttpServletRequest request, HttpServletResponse response) {
    response.addCookie(userService.logout(request));
    return "redirect:/api/recipes";
  }

  @GetMapping("/api/user/profile")
  public String renderProfilePage() {
    return "profile";
  }

  @PostMapping("api/user/profile")
  public String changeProfileData(@CookieValue(value = "Bearer") String token,
                                  Model model,
                                  @ModelAttribute("changedto") ChangeDTO changeDTO) {
    String username = jwtUtil.extractUsername(token);
    try {
      String res = userService.modifyUser(username, changeDTO);
      model.addAttribute("response", res);
    } catch (IncorrectPasswordException e) {
      model.addAttribute("response", e.getMessage());
    }
    return "profile";
  }

}