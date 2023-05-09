package com.todolist.repositories.impl;


import com.todolist.models.Task;
import com.todolist.repositories.TaskRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
    private Map<Long, Task> tasks = new HashMap<>();
    private Long TASK_ID = 0L;
    private Map<String,String> updates = new HashMap<>();

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
    public List<Task> getTasks() {
        return new LinkedList<>(tasks.values());
    }

    @Override
    public Task patchTask(Map <String, String> updates, Task taskToPatch) {
        if (updates.containsKey("title")) {
            taskToPatch.setTitle(updates.get("title"));
        }
        if (updates.containsKey("description")) {
            taskToPatch.setDescription(updates.get("description"));
        }
        if (updates.containsKey("color")) {
            taskToPatch.setColorAsName(updates.get("color"));
        }
        return taskToPatch;
    }
    @Override
    public Task updateTask(Task task, Task taskToUpdate){
        taskToUpdate.setTitle(task.getTitle());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setColor(task.getColor());
        return taskToUpdate;
    }

}
