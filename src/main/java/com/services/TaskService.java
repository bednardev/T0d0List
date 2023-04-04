package com.services;

import com.models.Task;
import com.repositories.impl.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public Task saveTask{
        this.taskRepository.saveTask(Task);
    }

    public List<Task> getTasks(){
        this.taskRepository.getTasks();
    }
}