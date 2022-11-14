package com.recipeapp.exceptions;

import com.recipeapp.exceptions.types.IncorrectPasswordException;
import com.recipeapp.exceptions.types.UsernameAlreadyExistException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleInvalidArguments(MethodArgumentNotValidException ex) {
    Map<String, String> errorMap = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error -> {
      errorMap.put(error.getField(), error.getDefaultMessage());
    });
    return errorMap;
  }

  @ExceptionHandler(UsernameAlreadyExistException.class)
  public String handleUsernameAlreadyExistException(UsernameAlreadyExistException ex) {
    return ex.getMessage();
  }

  @ExceptionHandler(IncorrectPasswordException.class)
  public String handleIncorrectPasswordException(IncorrectPasswordException ex) {
    return ex.getMessage();
  }

}
