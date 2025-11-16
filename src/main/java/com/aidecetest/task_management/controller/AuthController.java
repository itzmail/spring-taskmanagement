package com.aidecetest.task_management.controller;

import com.aidecetest.task_management.dto.LoginRequest;
import com.aidecetest.task_management.dto.LoginResponse;
import com.aidecetest.task_management.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(
    @RequestBody LoginRequest request
  ) {
    LoginResponse response = authService.login(request);
    return ResponseEntity.ok(response);
  }
}
