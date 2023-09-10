package com.todolist.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class TaskDto {

    private Long id;
    @NotEmpty()
    private String title;
    private String description;
    @NotEmpty()
    private String color;
    private TaskStatus status;

    public TaskDto(String title, String description, String color) {
        this.title = title;
        this.description = description;
        this.color = color;
    }

}