package com.aidecetest.task_management.dto;

import com.aidecetest.task_management.entity.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskRequest {

  @NotBlank(message = "Title is required")
  @Size(max = 255, message = "Title must not exceed 255 characters")
  private String title;

  @Size(max = 1000, message = "Description must not exceed 1000 characters")
  private String description;

  @NotNull(message = "Status harus diisi.")
  @Schema(
    implementation = String.class,
    example = "TO_DO",
    allowableValues = { "TO_DO", "IN_PROGRESS", "DONE" }
  )
  private Status status;
}
