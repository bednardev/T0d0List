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
    Long id = 0l;
    public TaskDto saveTask(TaskDto taskDto) {
        Task task = new Task(taskDto.getTitle(), taskDto.getDescription(), taskDto.getColor());
        taskRepository.saveTask(task);
        TaskDto taskDto1 = new TaskDto(task.getTitle(), task.getDescription(), task.getColor());
        taskDto1.setId(id);
        id++;
        return taskDto1;
    }
    public List<Task> getTasks(){ return taskRepository.getTasks(); }
}


