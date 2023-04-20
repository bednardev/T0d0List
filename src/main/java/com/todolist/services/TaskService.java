package com.todolist.services;

import com.todolist.models.Color;
import com.todolist.models.Task;
import com.todolist.models.TaskDto;
import com.todolist.repositories.impl.TaskRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepositoryImpl taskRepository;
    public TaskService(TaskRepositoryImpl taskRepository){
        this.taskRepository = taskRepository;
    }
    public TaskDto saveTask(TaskDto taskDto) {
        Task taskToSave = new Task(taskDto.getTitle(), taskDto.getDescription(), taskDto.getColor());
        Task task = taskRepository.saveTask(taskToSave);
        return new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColor());
    }

    public List<Task> getTasksByColor(Color color){
        return taskRepository.getTasks().stream().filter(c -> c.getColor().equals(color)).collect(Collectors.toList());
    }

    public List<Task> getTasks(){
        return taskRepository.getTasks();
    }

    }


