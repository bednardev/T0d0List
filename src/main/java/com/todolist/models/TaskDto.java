package com.todolist.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TaskDto {

    private Long id;
    @NotEmpty()
    private String title;
    private String description;
    @NotEmpty()
    private String color;
    @NotNull()
    private Long userId;

    private TaskStatus status;

    public TaskDto() {
    }

    public TaskDto(Long id, String title, String description, String color, Long userId, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.color = color;
        this.userId = userId;
    }

    public String getColor() {
        return color;
    }

    public String getTitle() {
        return title;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus() {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
