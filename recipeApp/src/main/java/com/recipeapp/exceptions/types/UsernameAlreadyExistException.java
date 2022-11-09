package com.recipeapp.exceptions.types;

public class UsernameAlreadyExistException extends RuntimeException {
  public UsernameAlreadyExistException() {
    super("A felhasználónév foglalt.");
  }
}