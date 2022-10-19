package com.recipeapp.controllers;

import com.recipeapp.models.dtos.login.LoginRequest;
import com.recipeapp.models.entities.MyUser;
import com.recipeapp.security.JwtUtil;
import com.recipeapp.services.UserService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
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

  @GetMapping("/api/user/login")
  public String renderLoginPage() {
    return "login";
  }

  @GetMapping("/api/user/logout")
  public String logout(HttpServletRequest request, HttpServletResponse response) {
    response.addCookie(userService.logout(request));
    return "redirect:/api/recipes";
  }

  @GetMapping("/api/user/register")
  public String renderRegisterUser() {
    return "register_user";
  }

  @PostMapping("/api/user/register")
  public String registerUser(@Valid @ModelAttribute("user") MyUser user) {
    userService.saveUser(user);
    return "redirect:/api/recipes";
  }

  @GetMapping("/api/secret")
  public ResponseEntity<?> secretPage(@CookieValue(value = "Bearer") String value) {
    System.out.println(value);
    String name = jwtUtil.extractUsername(value);
    System.out.println(jwtUtil.extractUsername(value));
    System.out.println(jwtUtil.validateToken(value, userService.loadUserByUsername(name)));
    System.out.println("With testname: " + jwtUtil.validateToken(value,
        userService.loadUserByUsername("d")));
    return ResponseEntity.ok(201);
  }

  @PostMapping("/api/auth")
  public String createToken(@ModelAttribute("loginrequest") LoginRequest loginRequest,
                            HttpServletResponse resp) throws Exception {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
              loginRequest.getPassword())
      );
    } catch (BadCredentialsException e) {
      throw new Exception("Incorrect Username or Password", e);
    }
    final UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());
    final String jwt = jwtUtil.generateToken(userDetails);
    resp.addCookie(userService.cookieGenerator(jwt));
    return "redirect:/api/recipes";
  }

}
