package com.todolist.repositories;

import com.todolist.models.Task;

import java.util.List;
import java.util.Map;

public interface TaskRepository {
    Task saveTask(Task task);

    List<Task> getTasks();
    Task patchTask(Map<String, String> updates, Task taskToPatch);
    Task updateTask(Task task, Task taskToUpdate);
}
