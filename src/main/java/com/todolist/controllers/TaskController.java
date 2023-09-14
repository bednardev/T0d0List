package com.todolist.controllers;

import com.todolist.models.TaskDto;
import com.todolist.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;

@RequestMapping("tasks")
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

    @GetMapping("page")
    public Page<TaskDto> getTasksAsPage(@RequestParam(value ="pageNo", required = false, defaultValue = "0") Integer pageNumber,
                                        @RequestParam(value = "size", required = false, defaultValue = "1") Integer pageSize,
                                        @RequestParam(value = "sort", required = false, defaultValue = "title") String sort,
                                        @RequestParam(value = "direction", required = false, defaultValue = "ASC") Sort.Direction direction) throws IllegalArgumentException {
        return taskService.getTasksAsPage(pageNumber, pageSize, sort, direction);
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

    @PatchMapping("/status/{id}")
    public TaskDto changeStatus (@PathVariable Long id) throws StatusDoneException {
        return taskService.changeStatus(id);
            }

    @DeleteMapping("{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.findById(id).
                orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        return taskService.deleteTask(id);
    }
}
