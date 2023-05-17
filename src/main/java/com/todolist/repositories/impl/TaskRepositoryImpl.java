/* package com.todolist.repositories.impl;


import com.todolist.models.Color;
import com.todolist.models.Task;
import com.todolist.repositories.TaskRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class TaskRepositoryImpl {
    private Map<Long, Task> tasks = new HashMap<>();
    private Long TASK_ID = 0L;

    @Override
    public Task saveTask(Task task) {
        task.setId(TASK_ID);
        task.setLastUpdatedAt(Instant.now());
        task.setCreatedAt(Instant.now());
        tasks.put(TASK_ID, task);
        TASK_ID += 1;
        return task;
    }

    @Override
    public List<Task> getTasks(Color color, String title) {
        Stream<Task> taskStream = new LinkedList<>(tasks.values()).stream();
        if (color != null) {
            taskStream = taskStream.filter(c -> c.getColor().equals(color));
        }
        if (title != null) {
            taskStream = taskStream.filter(c -> c.getTitle().contains(title));
        }
        return taskStream.collect(Collectors.toList());
    }

    @Override
    public Optional<Task> patchTask(Map<String, String> updates, Long id) {
        if (tasks.containsKey(id)) {
            Task taskToPatch = tasks.get(id);
            if (updates.containsKey("title")) {
                taskToPatch.setTitle(updates.get("title"));
            }
            if (updates.containsKey("description")) {
                taskToPatch.setDescription(updates.get("description"));
            }
            if (updates.containsKey("color")) {
                taskToPatch.setColorAsName(updates.get("color"));
            }
            return Optional.of(taskToPatch);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Task> updateTask(Task task, Long id) {
        if (tasks.containsKey(id)) {
            Task taskToUpdate = tasks.get(id);
            taskToUpdate.setTitle(task.getTitle());
            taskToUpdate.setDescription(task.getDescription());
            taskToUpdate.setColor(task.getColor());
            return Optional.of(taskToUpdate);
        }
        return Optional.empty();
    }

    @Override
    public void deleteTask(Long id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        }
    }
}

 */