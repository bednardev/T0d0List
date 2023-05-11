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

    public Optional<TaskDto> getTaskById(Long id) {
        return taskRepository.getTaskById(id)
                .map(task -> new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColorAsName()));
    }

    public TaskDto patchTask(Map<String, String> updates, TaskDto taskDtoToPatch) {
        Task taskToPatch = new Task(taskDtoToPatch.getTitle(), taskDtoToPatch.getDescription(), Color.valueOf(taskDtoToPatch.getColor()));
        Task task = taskRepository.patchTask(updates, taskToPatch);
        return new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColorAsName());
    }

    public TaskDto updateTask(TaskDto taskDto, TaskDto taskDtoToUpdate) {
        Task task = new Task(taskDto.getTitle(), taskDto.getDescription(), Color.valueOf(taskDto.getColor()));
        Task taskToUpdate = new Task(taskDtoToUpdate.getTitle(), taskDtoToUpdate.getDescription(), Color.valueOf(taskDtoToUpdate.getColor()));
        Task taskUpdate = taskRepository.updateTask(task, taskToUpdate);
        return new TaskDto(taskUpdate.getId(),taskUpdate.getTitle(),taskUpdate.getDescription(), taskUpdate.getColorAsName());
    }
}

