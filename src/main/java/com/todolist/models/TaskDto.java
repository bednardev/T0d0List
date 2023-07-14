package com.todolist.models;

import jakarta.validation.constraints.NotEmpty;

public class TaskDto {

    private Long id;
    @NotEmpty()
    private String title;
    private String description;
    @NotEmpty()
    private String color;
    private String status;

    public TaskDto(Long id, String title, String description, String color, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.color = color;
        this.status=status;
    }

    public String getColor() {
        return color;
    }

    public String getTitle() {
        return title;
    }
    public String getStatus() {
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
}
