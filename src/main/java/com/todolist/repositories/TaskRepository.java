package com.todolist.repositories;

import com.todolist.models.Color;
import com.todolist.models.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TaskRepository {
    Task saveTask(Task task);

    List<Task> getTasks(Color color, String title);

    Optional<Task> patchTask(Map<String, String> updates, Long id);

    Optional<Task> updateTask(Task task, Long id);

    void deleteTask(Long id);
}
