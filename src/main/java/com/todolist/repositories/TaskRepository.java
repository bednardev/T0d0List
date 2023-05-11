package com.todolist.repositories;

import com.todolist.models.Color;
import com.todolist.models.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TaskRepository {
    Task saveTask(Task task);

    List<Task> getTasks(Color color, String title);

    Task patchTask(Map<String, String> updates, Task taskToPatch);

    Task updateTask(Task task, Task taskToUpdate);

    Optional<Task> getTaskById(Long id);

}
