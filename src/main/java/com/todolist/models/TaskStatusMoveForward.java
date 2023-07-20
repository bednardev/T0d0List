package com.todolist.models;

import com.todolist.controllers.StatusDoneException;

public interface TaskStatusMoveForward {
    public TaskStatus moveForward() throws StatusDoneException;
}
