package com.todolist.controllers;

import com.todolist.models.Color;
import com.todolist.models.Task;
import com.todolist.models.TaskDto;
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
    public TaskDto saveTask(@RequestBody TaskDto taskDto) {
        return taskService.saveTask(taskDto);
    }

    @GetMapping
    public List<Task> getTasks(@RequestParam(value = "color", required = false) Color color,
                               @RequestParam(value = "title", required = false) String title) {
        return taskService.getTasks(color, title);
    }

    @PostMapping("/updatetask")
    public TaskDto updateTask(@RequestBody TaskDto taskDto){
return taskService.updateTask(taskDto);
    }

}


