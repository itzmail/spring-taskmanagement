package com.aidecetest.task_management.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class LoginResponse {

  private String token;

  @Builder.Default
  private String tokenType = "Bearer";
}
