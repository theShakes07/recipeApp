package com.recipeapp.exceptions;

public class ErrorResponse {

  private String status;
  private String message;

  public ErrorResponse(String message) {
    this.status = "error";
    this.message = message;
  }
}
