package com.recipeapp.exceptions.types;

public class IncorrectPasswordException extends RuntimeException {
  public IncorrectPasswordException() {
    super("Hibás jelszó!");
  }
}
