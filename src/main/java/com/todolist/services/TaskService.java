package com.todolist.services;

import com.todolist.models.Color;
import com.todolist.models.Task;
import com.todolist.models.TaskDto;
import com.todolist.repositories.impl.TaskRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepositoryImpl taskRepository;

    public TaskService(TaskRepositoryImpl taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskDto saveTask(TaskDto taskDto) {
        Task taskToSave = new Task(taskDto.getTitle(), taskDto.getDescription(), Color.valueOf(taskDto.getColor()));
        Task task = taskRepository.saveTask(taskToSave);
        return new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColorAsName());
    }

    public List<TaskDto> getTasks(Color color, String title) {
        return taskRepository.getTasks(color, title)
                .stream()
                .map(task -> new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColorAsName())).
                collect(Collectors.toList());
    }

    public Optional<TaskDto> patchTask(Map<String, String> updates, Long id) {
        return taskRepository.patchTask(updates, id).
                stream().
                findFirst().
                map(task -> new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColorAsName()));
    }

    public Optional<TaskDto> updateTask(Task taskToUpdate, Long id) {
        return taskRepository.updateTask(taskToUpdate, id).
                stream().
                findFirst().
                map(task -> new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColorAsName()));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteTask(id);
    }
}

