package com.todolist.repositories.impl;


import com.todolist.models.AuditDates;
import com.todolist.models.Task;
import com.todolist.repositories.TaskRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
    private Map<Long, Task> tasks = new HashMap<>();
    private Long TASK_ID = 0L;
    private AuditDates auditDates;

    @Override
    public Task saveTask(Task task) {
        task.setId(TASK_ID);
        task.setAuditDates(auditDates);
        tasks.put(TASK_ID, task);
        TASK_ID += 1;
        return task;
    }

    @Override
    public List<Task> getTasks() {
        return new LinkedList<>(tasks.values());
    }

}
