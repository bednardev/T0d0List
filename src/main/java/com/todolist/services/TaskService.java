package com.todolist.services;

import com.todolist.models.Color;
import com.todolist.models.Task;
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
    public Task saveTask(Task task){
      return taskRepository.saveTask(task);
    }

    public List<Task> getTasks(Color color){
        return taskRepository.getTasks().stream().filter(c -> c.getColor().equals(color)).collect(Collectors.toList());
    }
    }


