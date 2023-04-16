package com.todolist.services;

import com.todolist.models.AuditDates;
import com.todolist.models.Task;
import com.todolist.models.TaskDto;
import com.todolist.repositories.impl.TaskRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepositoryImpl taskRepository;
    private final TaskDto taskDto;

    private Map<Long, TaskDto> tasksDto = new HashMap<>();
    private Map<Long, Task> tasks = new HashMap<>();
    private Long TASK_ID = 0L;

    public TaskService(TaskRepositoryImpl taskRepository, TaskDto taskDto){
        this.taskRepository = taskRepository;
        this.taskDto = taskDto;
    }
    public Task saveTask(Task task) {
        return taskRepository.saveTask(task);
    }
    public TaskDto saveTaskDto(TaskDto taskDto) {
        tasksDto.values() = tasks.values().stream().map(task -> taskDto).collect(Collectors.toList());
        return taskDto;
    }
    public List<Task> getTasks(){
        return taskRepository.getTasks();
    }
}


