package com.recipeapp.security;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class CookieValidator {

  private JwtUtil jwtUtil;

  public CookieValidator(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @Pointcut("within(@org.springframework.stereotype.Controller *)")
  public void controller() {
  }

  @After("controller()")
  public void cookieChecker(JoinPoint joinPoint) {
    HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(
            RequestContextHolder.getRequestAttributes()))
            .getRequest();

    if (request.getCookies() == null) {
      return;
    }

    HttpServletResponse response = ((ServletRequestAttributes) Objects.requireNonNull(
        RequestContextHolder.getRequestAttributes()))
        .getResponse();

    Optional<Cookie> maybeCookie = Arrays.stream(request.getCookies())
        .filter(cookie -> cookie.getName().equals("Bearer"))
        .findFirst();

    maybeCookie.ifPresent(cookie -> {
      if (!jwtUtil.validateToken(cookie.getValue()) && response != null) {
        response.addCookie(JwtUtil.buildLogoutToken());
      }
    });
  }
}