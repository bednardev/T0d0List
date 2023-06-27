package com.todolist.controllers;

import com.todolist.models.TaskDto;
import com.todolist.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;

@RequestMapping("/tasks")
@RestController
public class TaskController {
    private final TaskService taskService;
    private final ControllerExceptionHandler controllerExceptionHandler;

    public TaskController(TaskService taskService, ControllerExceptionHandler controllerExceptionHandler) {
        this.taskService = taskService;
        this.controllerExceptionHandler = controllerExceptionHandler;
    }

    @PostMapping
    public TaskDto saveTask(@RequestBody @Valid TaskDto taskDto) {
        return taskService.saveTask(taskDto);
    }

    @GetMapping
    public List<TaskDto> getTasks(@RequestParam(value = "color", required = false) String color,
                                  @RequestParam(value = "title", required = false) String title) {
        return taskService.getTasks(color, title);
    }

    @PutMapping("{id}")
    public TaskDto updateTask(@RequestBody TaskDto taskDtoToUpdate, @PathVariable Long id) {
        return taskService.updateTask(taskDtoToUpdate, id).
                orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("{id}")
    public TaskDto patchTask(@RequestBody Map<String, String> updates, @PathVariable Long id) {
        return taskService.patchTask(updates, id).
                orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.findById(id).
                orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        return taskService.deleteTask(id);
    }
}
