package com.todolist.controllers;

import com.todolist.models.TaskDto;
import com.todolist.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;

@RequestMapping("/tasks")
@RestController
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
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

    @GetMapping("/page")
    public Page<TaskDto> getTasksAsPage(@RequestParam(value ="pageNo", required = false, defaultValue = "1") Integer pageNumber,
                                        @RequestParam(value = "size", required = false, defaultValue = "1") Integer pageSize,
                                        @RequestParam(value ="sortBy", required = false) String sortBy) {
        return taskService.getTasksAsPage(pageNumber, pageSize, sortBy);
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
