package com.aidecetest.task_management.service;

import com.aidecetest.task_management.dto.TaskRequest;
import com.aidecetest.task_management.entity.Task;
import com.aidecetest.task_management.repository.TaskRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

  private final TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public Task createTask(TaskRequest request) {
    Task task = new Task();

    BeanUtils.copyProperties(request, task);

    return taskRepository.save(task);
  }

  public List<Task> getAllTasks() {
    return taskRepository.findAll();
  }

  public Task getTaskById(Long taskId) {
    Optional<Task> taskOptional = taskRepository.findById(taskId);

    if (taskOptional.isEmpty()) {
      throw new RuntimeException("Task not found");
    }

    return taskOptional.get();
  }

  public Task updateTask(Long taskId, TaskRequest request) {
    Optional<Task> taskOptional = taskRepository.findById(taskId);

    if (taskOptional.isEmpty()) {
      throw new RuntimeException("Update failed, task not found");
    }

    Task taskToUpdate = taskOptional.get();

    BeanUtils.copyProperties(request, taskToUpdate);

    return taskRepository.save(taskToUpdate);
  }

  public void deleteTask(Long taskId) {
    if (!taskRepository.existsById(taskId)) {
      throw new RuntimeException("Delete failed, task not found");
    }

    taskRepository.deleteById(taskId);
  }

}
