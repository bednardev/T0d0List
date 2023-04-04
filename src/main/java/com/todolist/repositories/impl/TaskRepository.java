package com.todolist.repositories.impl;


import com.todolist.models.Task;
import com.todolist.repositories.ToDoInterface;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TaskRepository implements ToDoInterface {

    private static Map<Long, Task> tasks = new HashMap<>();

    public TaskRepository(Map<Long, Task> tasks) {
        this.tasks = tasks;
    }

    private Long TASK_ID = 0L;


    public Task saveTask(Task task) {
        task.setId(TASK_ID);
        tasks.put(TASK_ID, task);
        TASK_ID += 1;
        return task;
    }

    @Override
    public List<Task> getTasks() {
        return new LinkedList<>(tasks.values());
    }

}
