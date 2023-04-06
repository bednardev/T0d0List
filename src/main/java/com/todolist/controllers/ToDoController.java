package com.todolist.controllers;

import com.todolist.models.Task;
import com.todolist.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tasks")
@RestController
public class ToDoController {
    private final TaskService taskService;

    public ToDoController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task saveTask(@RequestBody Task task) {
        return taskService.saveTask(task);
   }

    @GetMapping
      public List<Task> getTasks() {
        return taskService.getTasks();
    }
}