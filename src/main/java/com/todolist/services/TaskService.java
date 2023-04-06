package com.todolist.services;

import com.todolist.models.Task;
import com.todolist.repositories.impl.TaskRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepositoryImpl taskRepository;

    public TaskService(TaskRepositoryImpl taskRepository){
        this.taskRepository = taskRepository;
    }
    public Task saveTask(Task task){
      return taskRepository.saveTask(task);
    }

    public List<Task> getTasks(){
        return taskRepository.getTasks();
    }
}