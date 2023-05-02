package com.todolist.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public class TaskDto {
    private Long id;
    @NotEmpty(message="400 Error: field could not be empty")
    private String title;
    private String description;
    @NotNull(message = "400 Error: wrong color value")
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
    public Long getId() {
        return id;
    }
}
