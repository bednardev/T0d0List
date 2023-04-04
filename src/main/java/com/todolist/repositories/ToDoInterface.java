package com.todolist.repositories;

import com.todolist.models.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoInterface {
    public Task saveTask(Task task);
    public List<Task> getTasks();
}
