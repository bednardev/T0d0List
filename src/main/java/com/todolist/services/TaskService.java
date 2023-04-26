package com.todolist.services;

import com.todolist.models.Color;
import com.todolist.models.Task;
import com.todolist.models.TaskDto;
import com.todolist.repositories.impl.TaskRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TaskService {

    private final TaskRepositoryImpl taskRepository;

    public TaskService(TaskRepositoryImpl taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskDto saveTask(TaskDto taskDto) {
        Task taskToSave = new Task(taskDto.getTitle(), taskDto.getDescription(), taskDto.getColor());
        Task task = taskRepository.saveTask(taskToSave);
        return new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColor());
    }

    public List<Task> getTasks(Color color, String title) {
        Stream<Task> taskStream = taskRepository.getTasks().stream();
        if (color != null) {
            taskStream = taskStream.filter(c -> c.getColor().equals(color));
        }
        if (title != null) {
            taskStream = taskStream.filter(c -> c.getTitle().contains(title));
        }
        return taskStream.collect(Collectors.toList());
    }

    public TaskDto updateTask(TaskDto taskDto) {
        TaskDto taskToUpdate = new TaskDto(taskDto.getId(), taskDto.getTitle(), taskDto.getDescription(), taskDto.getColor());
        TaskDto taskDto = taskRepository.updateTask(taskToUpdate);
        return new TaskDto(task.getId(),task.getTitle(),task.getDescription(),task.getColor());
    }

}

