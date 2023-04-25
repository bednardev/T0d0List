package com.todolist.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TaskDto {

    private Long id;
    @NotEmpty(message="field could not be empty")
    private String title;
    private String description;
    @NotNull
    private Color color;

    public TaskDto(Long id, String title, String description, Color color) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public String getTitle() {
        return title;
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
