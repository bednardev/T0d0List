package com.todolist.controllers;

import com.todolist.models.Color;
import com.todolist.models.TaskDto;
import com.todolist.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/tasks")
@RestController
public class ToDoController {
    private final TaskService taskService;

    public ToDoController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public TaskDto saveTask(@RequestBody @Valid TaskDto taskDto) {
        return taskService.saveTask(taskDto);
    }

    @GetMapping
    public List<TaskDto> getTasks(@RequestParam(value = "color", required = false) Color color,
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
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("status", "400", "errors", prepareMapFromValidationErrors(ex)));
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Map<String, String>> handleHttpClientErrorException(HttpClientErrorException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Error", "status 404, id not exist"));
    }

    private String prepareMapFromValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorMap.put(fieldName, errorMessage);
        });
        return errorMap.toString();
    }
}
