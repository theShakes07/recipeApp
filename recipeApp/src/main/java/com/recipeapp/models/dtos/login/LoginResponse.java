package com.recipeapp.models.dtos.login;

public class LoginResponse {

  String status;
  String name;

  public LoginResponse(String name) {
    this.status = "Logged in";
    this.name = name;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
