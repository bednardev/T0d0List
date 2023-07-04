package com.todolist.models;

import jakarta.validation.constraints.NotEmpty;

public class TaskDto {

    private Long id;
    @NotEmpty()
    private String title;
    private String description;
    @NotEmpty()
    private String color;

    private Long userId;

    public TaskDto(){
    };

    public TaskDto(Long id, String title, String description, String color) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.color = color;
    }

    public TaskDto(Long id, String title, String description, String color, Long userId) {
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
