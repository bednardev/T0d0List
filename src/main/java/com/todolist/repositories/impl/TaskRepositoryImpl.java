package com.todolist.repositories.impl;


import com.todolist.models.Task;
import com.todolist.repositories.TaskRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
    private Map<Long, Task> tasks = new HashMap<>();
    private Long TASK_ID = 0L;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private LocalDateTime now = LocalDateTime.now();
    private String creationDate = dtf.format(now);

    @Override
    public Task saveTask(Task task) {
        task.setId(TASK_ID);
        tasks.put(TASK_ID, task);
        task.setCreatedAt(creationDate);
        task.setLastUpdatedAt(creationDate);
        TASK_ID += 1;
        return task;
    }

    @Override
    public List<Task> getTasks() {
        return new LinkedList<>(tasks.values());
    }

}
