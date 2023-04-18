package com.todolist.services;

import com.todolist.models.Task;
import com.todolist.models.TaskDto;
import com.todolist.repositories.impl.TaskRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepositoryImpl taskRepository;
    public TaskService(TaskRepositoryImpl taskRepository){
        this.taskRepository = taskRepository;
    }
    public TaskDto saveTask(TaskDto taskDto) {
        Task taskToSave = new Task(taskDto.getTitle(), taskDto.getDescription(), taskDto.getColor());
        Task task = taskRepository.saveTask(taskToSave);
        taskDto.setId(task.getId());
        return new TaskDto(task.getTitle(), task.getDescription(), task.getColor());
    }
    public List<Task> getTasks(){ return taskRepository.getTasks(); }
}


