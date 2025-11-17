package com.aidecetest.task_management.controller;

import com.aidecetest.task_management.dto.ApiResponse;
import com.aidecetest.task_management.dto.TaskRequest;
import com.aidecetest.task_management.entity.Task;
import com.aidecetest.task_management.service.TaskService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks") // Base path: /api/tasks
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<Task>>> getAllTasks() {
    List<Task> tasks = taskService.getAllTasks();

    ApiResponse<List<Task>> response = ApiResponse.<List<Task>>builder()
      .status(true)
      .message("Succes get data")
      .data(tasks)
      .build();

    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<Task>> getTaskById(@PathVariable Long id) {
    Task task = taskService.getTaskById(id);

    ApiResponse<Task> response = ApiResponse.<Task>builder()
      .status(true)
      .message("Get detail task")
      .data(task)
      .build();

    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<ApiResponse<Task>> createTask(
    @Valid @RequestBody TaskRequest request
  ) {
    Task createdTask = taskService.createTask(request);

    ApiResponse<Task> response = ApiResponse.<Task>builder()
      .status(true)
      .message("Task created successfully")
      .data(createdTask)
      .build();

    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<Task>> updateTask(
    @PathVariable Long id,
    @Valid @RequestBody TaskRequest request
  ) {
    Task updatedTask = taskService.updateTask(id, request);

    ApiResponse<Task> response = ApiResponse.<Task>builder()
      .status(true)
      .message("Task updated successfully")
      .data(updatedTask)
      .build();

    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> deleteTask(@PathVariable Long id) {
    taskService.deleteTask(id);

    ApiResponse<Void> response = ApiResponse.<Void>builder()
      .status(true)
      .message("Task deleted successfully")
      .data(null)
      .build();

    return ResponseEntity.ok(response);
  }
}
