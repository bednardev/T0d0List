package com.todolist.models;

import com.todolist.controllers.StatusDoneException;

public enum TaskStatus implements TaskStatusMoveForward {
    BACKLOG {
        @Override
        public TaskStatus moveForward() {
            return TO_DO;
        }
    },
    TO_DO {
        @Override
        public TaskStatus moveForward() {
            return IN_PROGRESS;
        }
    },
    IN_PROGRESS {
        @Override
        public TaskStatus moveForward() {
            return DONE;
        }
    },
    DONE {
        @Override
        public TaskStatus moveForward() throws StatusDoneException {
            throw new StatusDoneException();
        }
    }
}