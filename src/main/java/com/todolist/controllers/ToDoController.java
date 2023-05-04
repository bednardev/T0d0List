package com.todolist.controllers;

import com.todolist.models.Color;
import com.todolist.models.Task;
import com.todolist.models.TaskDto;
import com.todolist.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public List<Task> getTasks(@RequestParam(value = "color", required = false) Color color,
                               @RequestParam(value = "title", required = false) String title) {
        return taskService.getTasks(color, title);
    }

@PatchMapping("/tasks/{id}")
@ResponseStatus(value = HttpStatus.NO_CONTENT)
public void patchTask(@RequestBody Map<String, Task> updates, @PathVariable Long id){
        Optional<Task> taskToPatch = taskService.getTaskById(id);
        if (updates.containsKey("title")){
            taskToPatch.setTitle(updates.get("title"));
        }
        if (updates.containsKey("color")){
            taskToPatch.setColor(updates.get("color"));
        }
        if (updates.containsKey("description")){
            taskToPatch.setDescription(updates.get("description"));
        }
}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("status", "400", "errors", prepareMapFromValidationErrors(ex)));
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
