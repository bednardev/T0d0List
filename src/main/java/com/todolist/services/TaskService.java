package com.todolist.services;

import com.todolist.models.Color;
import com.todolist.models.Task;
import com.todolist.models.TaskDto;
import com.todolist.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskDto saveTask(TaskDto taskDto) {
        Task taskToSave = new Task(taskDto.getTitle(), taskDto.getDescription(), Color.valueOf(taskDto.getColor().toUpperCase()));
        taskToSave.setCreatedAt(Instant.now());
        Task task = taskRepository.save(taskToSave);
        return new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColorAsName());
    }

    public List<TaskDto> getTasks(Color color, String title) {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(t -> tasks.add(t));
        Stream<Task> taskStream = tasks.stream();
        if (color != null) {
            taskStream = taskStream.filter(c -> c.getColor().equals(color));
        }
        if (title != null) {
            taskStream = taskStream.filter(c -> c.getTitle().contains(title));
        }
        return taskStream
                .map(task -> new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColorAsName())).collect(Collectors.toList());
    }

    public Optional<TaskDto> updateTask(TaskDto taskDtoToUpdate, Long id) {
        Task taskToUpdate = new Task(id, taskRepository.findById(id).get().getCreatedAt(), taskDtoToUpdate.getTitle(), taskDtoToUpdate.getDescription(), Color.valueOf(taskDtoToUpdate.getColor()));
        taskToUpdate.setLastUpdatedAt(Instant.now());
        return taskRepository.findById(id)
                .map(t -> taskRepository.save(taskToUpdate))
                .map(task -> new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColorAsName()));
    }

    private Task setTaskToPatch(Map<String, String> updates, Task taskToPatch){
        if (updates.containsKey("title")) {
            taskToPatch.setTitle(updates.get("title"));
        }
        if (updates.containsKey("description")) {
            taskToPatch.setDescription(updates.get("description"));
        }
        if (updates.containsKey("color")) {
            taskToPatch.setColorAsName(updates.get("color"));
        }
        taskToPatch.setLastUpdatedAt(Instant.now());
        taskRepository.save(taskToPatch);
        return taskToPatch;
    }
    public Optional<TaskDto> patchTask(Map<String, String> updates, Long id) {
        return taskRepository.findById(id)
                .map(t -> setTaskToPatch(updates, t))
                .map(task -> new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getColorAsName()));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}