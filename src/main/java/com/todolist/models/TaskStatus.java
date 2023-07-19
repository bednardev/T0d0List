package com.todolist.models;

import com.todolist.controllers.StatusDoneException;

public enum TaskStatus implements TaskStatusMoveForward {
    BACKLOG,
    TO_DO,
    IN_PROGRESS,
    DONE;

    @Override
    public TaskStatus moveForward(TaskStatus taskStatus) throws StatusDoneException {
        switch (taskStatus) {
            case BACKLOG:
                return TO_DO;
            case TO_DO:
                return IN_PROGRESS;
            case IN_PROGRESS:
                return DONE;
        }
        throw new StatusDoneException();
    }
}