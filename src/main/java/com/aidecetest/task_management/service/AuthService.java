package com.aidecetest.task_management.service;

import com.aidecetest.task_management.dto.LoginRequest;
import com.aidecetest.task_management.dto.LoginResponse;
import com.aidecetest.task_management.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private final JwtTokenProvider jwtTokenProvider;

  @Autowired
  public AuthService(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  public LoginResponse login(LoginRequest request) {
    if (
      "admin".equals(request.getUsername()) &&
      "admin123".equals(request.getPassword())
    ) {
      String token = jwtTokenProvider.generateToken(request.getUsername());

      return LoginResponse.builder().token(token).build();
    } else {
      throw new BadCredentialsException("Username atau password salah");
    }
  }
}
