package com.recipeapp.models.dtos.login;

public class JwtResponse {

  private String status;
  private String token;

  public JwtResponse(String token) {
    this.status = "ok";
    this.token = token;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
