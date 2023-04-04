package com.repositories;

import com.models.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoInterface {
    public Task saveTask();
    public List<Task> getTasks();
}
