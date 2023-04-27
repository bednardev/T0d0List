package com.todolist.repositories;

import com.todolist.models.Task;

import java.util.List;

public interface TaskRepository {
    Task saveTask(Task task);

    List<Task> getTasks();
}
